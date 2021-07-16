package com.xunle.eduservice.client;

import com.xunle.commonutils.R;
import com.xunle.eduservice.entity.chapter.EduVideo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xunle
 */
@Component
@FeignClient(name = "com.xunle.msm.service-vod", fallback = VodCircuitBreakerClient.class)
public interface VodClient {

    //定义调用方法路径
    @DeleteMapping("/eduvod/video/remove/{videoId}")
    R removeAlyVideo(@PathVariable("videoId") String videoId);

    //删除多个视频
    @DeleteMapping("eduvod/video/removeBatch")
    R removeBatchAlyVideo(@RequestParam("videoList")List<String> videoList);
}
