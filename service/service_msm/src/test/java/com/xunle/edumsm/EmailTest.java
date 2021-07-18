package com.xunle.edumsm;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;

/**
 * 邮箱测试
 * @author xunle
 */
public class EmailTest {

    @Test
    public void send() {
        //收件人
        String target = " ";
        //发件人
        String source = " ";
        //邮箱授权码
        String auth = " ";
        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.qq.com");
            email.setCharset("utf-8");

            email.addTo(source);
            email.setFrom(source, "xunle");

            email.setAuthentication(source,auth);

            email.setSubject("测试");
            email.setMsg("测试测试测试测试测试测试测试测试测试测试");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
