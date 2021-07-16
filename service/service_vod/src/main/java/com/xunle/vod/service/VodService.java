package com.xunle.vod.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xunle
 */
public interface VodService {


    String uploadVideo(MultipartFile file);

    void removeVideo(String videoId);

    void removeVideoList(List videoList);
}
