package com.jun.my_mango.admin.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.jun.my_mango.admin.model.SysUser;
import com.jun.my_mango.admin.security.JwtAuthenticationToken;
import com.jun.my_mango.admin.service.SysUserService;
import com.jun.my_mango.admin.util.PasswordUtils;
import com.jun.my_mango.admin.util.SecurityUtils;
import com.jun.my_mango.admin.vo.LoginVO;
import com.jun.my_mango.common.utils.IOUtils;
import com.jun.my_mango.core.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @author: Liusu
 * @date: 2022年11月11日13:17
 */
@RestController
//@RequestMapping(value = "")
@Api(value = "登录模块", tags = "A.登录模块")
public class SysLoginController {
    @Autowired
    private Producer producer;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "captcha.jpg")
    @ApiOperation(value = "获取验证码图片")
    public HttpResult  captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 将验证码放到redis中，生成对应的id和失效时间{“captchaId”： “text"}
        String captchaId = UUID.randomUUID().toString();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存验证码到session ------ 弃用
        //request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        //ServletOutputStream out = response.getOutputStream();
        //ImageIO.write(image, "jpg", out);
        //IOUtils.closeQuietly(out);
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            //输出流输出图片,格式为jpg
            ImageIO.write(image,"jpeg",outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(outputStream.toByteArray());
            String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r|\n","");
            Map<String,String> result = new HashMap<String,String>();
            result.put("captchaId",captchaId);
            result.put("captchaBase64",captchaBase64);
            redisTemplate.opsForValue().set(captchaId, text, 5, TimeUnit.MINUTES);  // 存到redis中去
            return HttpResult.ok(result);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return HttpResult.error("服务器繁忙，清稍后重试！");
    }

    @ApiOperation(value = "登录接口")
    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginVO loginVO, HttpServletRequest request) throws IOException{
        String username = loginVO.getAccount();
        String password = loginVO.getPassword();
        String captcha = loginVO.getCaptcha();
        String captchaId = loginVO.getCaptchaId();
        // 从session中获取之前保存的验证码，跟前台传来的验证码进行比较
        // Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 从redis中获取之前保存的验证码
        String redisCaptcha = (String) redisTemplate.opsForValue().get(captchaId);
        if (redisCaptcha == null){
            return HttpResult.error("验证码已失效");
        }
        if (!captcha.equalsIgnoreCase((String) redisCaptcha)){
            return HttpResult.error("验证码不正确");
        }
        redisTemplate.delete(captchaId);  // 验证码匹配成功，删除验证码
        // 用户信息
        SysUser user = sysUserService.findByName(username);
        // 账号不存在，密码错误
        if (user == null){
            return HttpResult.error("账号不存在");
        }
        if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())){
            return HttpResult.error("密码不正确");
        }
        // 账号锁定
        if (user.getStatus() == 0){
            return HttpResult.error("账号已被锁定，请联系管理员");
        }
        // 系统登录认证
        JwtAuthenticationToken token = SecurityUtils.login(request, username, password, authenticationManager);
        return HttpResult.ok(token);
    }


}
