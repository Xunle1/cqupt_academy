package com.xunle.eduucenter.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;

import java.util.Random;

/**
 * @author xunle
 */
public class EmailUtils {

    @Value("${mail.sender}")
    static String from;
    @Value("${mail.host}")
    static String host;
    @Value("${mail.username}")
    static String username;
    @Value("${mail.password}")
    static String password;
    @Value("${mail.default-encoding}")
    static String charSet;

    public static String sendEmail(String emailTo) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(host);
            email.setCharset(charSet);

            email.addTo(emailTo);
            email.setFrom(from, "xunle");

            email.setAuthentication(username,password);

            email.setSubject("邮学院");
            String code = getCode();
            email.setMsg("您的验证码是：" + code);
            email.send();
            return code;
        } catch (EmailException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
