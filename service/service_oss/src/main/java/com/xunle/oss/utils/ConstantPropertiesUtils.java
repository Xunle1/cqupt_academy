package com.xunle.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xunle
 */

/**
 * 实现Spring初始化接口，当Spring加载之后，执行接口的一个方法
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {
    /**
     * 定义公开静态常量
     */
    public static String END_POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;

    //读取配置文件内容

    @Value("${aliyun.oss.file.endPoint}")
    private String endPoint;

    @Value("${aliyun.oss.file.keyId}")
    private String keyId;

    @Value("${aliyun.oss.file.keySecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endPoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
