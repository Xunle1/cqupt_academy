package com.xunle.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author xunle
 */
public interface OssService {
    /**
     * 上传头像
     * @param file
     * @return 返回
     */
    String uploadAvatar(MultipartFile file);
}
