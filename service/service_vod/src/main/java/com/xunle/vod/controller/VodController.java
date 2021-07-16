package com.xunle.vod.controller;

import com.xunle.commonutils.R;
import com.xunle.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xunle
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("/upload")
    public R uploadVideo(MultipartFile file) {
        String id = vodService.uploadVideo(file);
        return R.ok().data("videoId",id);
    }

    //删除视频
    @DeleteMapping("/remove/{videoId}")
    public R removeVideo(@PathVariable String videoId) {
        vodService.removeVideo(videoId);
        return R.ok();
    }

    //删除多个视频
    @DeleteMapping("removeBatch")
    public R removeVideoList(@RequestParam("videoList") List videoList) {
        vodService.removeVideoList(videoList);

        return R.ok();
    }
}
