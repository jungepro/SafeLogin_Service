package com.ruoyi.framework.web.service;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ip.AddressUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.shuai.domain.HuiFactory;
import com.ruoyi.shuai.service.IHuiFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 登录校验方法
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private IHuiFactoryService factoryService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        Integer tryCount = redisCache.getCacheObject(username);
        LambdaQueryWrapper<HuiFactory> query = new LambdaQueryWrapper<>();
        query.eq(HuiFactory::getAccount, username);
        HuiFactory factory = factoryService.getOne(query);
        if (tryCount != null) {
            if (tryCount > factory.getMaxlogin()) {
                factoryService.expireData(username,0);
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "重试次数受限！"));
                throw new CustomException("重试次数受限！");
            }
        } else {
            tryCount = 0;
        }
        if (!Objects.equals(username, "admin")) {
            redisCache.setCacheObject(username, tryCount, 30, TimeUnit.MINUTES);
        }
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            tryCount++;
            if (!Objects.equals(username, "admin")) {
                redisCache.setCacheObject(username, tryCount, 30, TimeUnit.MINUTES);
            }
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            tryCount++;
            if (!Objects.equals(username, "admin")) {
                redisCache.setCacheObject(username, tryCount, 30, TimeUnit.MINUTES);
            }
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        if (!Objects.equals(username, "admin")) {
            if (factory == null) {
                tryCount++;
                redisCache.setCacheObject(username, tryCount, 30, TimeUnit.MINUTES);
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "厂家信息未配置！"));
                throw new CustomException("账号异常！");
            }
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());

            if (!ip.equals(factory.getIp())) {
                tryCount++;
                redisCache.setCacheObject(username, tryCount, 30, TimeUnit.MINUTES);
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "IP不允许访问！"));
                throw new CustomException("IP不允许访问！");
            }

            if (new Date().after(factory.getExpiretime())) {
                tryCount++;
                factoryService.expireData(username,0);

                redisCache.setCacheObject(username, tryCount, 30, TimeUnit.MINUTES);
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "账号已过期！"));
                throw new CustomException("账号已过期！");
            }

            if (factory.getZt() == 0) {
                tryCount++;
                redisCache.setCacheObject(username, tryCount, 30, TimeUnit.MINUTES);
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "账号已锁定！"));
                throw new CustomException("账号已锁定！");
            }
        }

        // 用户验证
        Authentication authentication = null;
        try {
            tryCount++;
            if (!Objects.equals(username, "admin")) {
                redisCache.setCacheObject(username, tryCount, 30, TimeUnit.MINUTES);
            }
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                tryCount++;
                if (!Objects.equals(username, "admin")) {
                    redisCache.setCacheObject(username, tryCount, 30, TimeUnit.MINUTES);
                }
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                tryCount++;
                if (!Objects.equals(username, "admin")) {
                    redisCache.setCacheObject(username, tryCount, 30, TimeUnit.MINUTES);
                }
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }



redisCache.deleteObject(username);
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }
}
