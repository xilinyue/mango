package com.jun.my_mango.admin.vo;

/**
 * @Description: 登录接口封装对象
 * @author: Liusu
 * @date: 2022年11月11日14:57
 */
public class LoginVO {
    private String account;
    private String password;
    private String captcha;
    private String captchaId;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }
}
