package com.xunle.edumsm.service.impl;

import com.xunle.edumsm.service.MsmService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author xunle
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Value("${mail.sender}")
    private String from;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;
    @Value("${mail.default-encoding}")
    private String charSet;

    @Override
    public void sendEmail(String emailTo) {
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(host);
            email.setCharset(charSet);

            email.addTo(emailTo);
            email.setFrom(from, "xunle");

            email.setAuthentication(username,password);

            email.setSubject("邮学院");
            String code = getCode();
            //设置有效时间
            redisTemplate.opsForValue().set(emailTo,code,5, TimeUnit.MINUTES);

            email.setMsg("您的验证码是：" + code);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    private String getCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
