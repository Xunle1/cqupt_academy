package com.xunle.edumsm;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;

/**
 * @author xunle
 */
public class EmailTest {

    @Test
    public void send() {

        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName("smtp.qq.com");
            email.setCharset("utf-8");

            email.addTo("1614990979@qq.com");
            email.setFrom("1601315809@qq.com", "xunle");

            email.setAuthentication("1601315809@qq.com","xpnsajskvfokhdic");

            email.setSubject("测试");
            email.setMsg("测试测试测试测试测试测试测试测试测试测试");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
