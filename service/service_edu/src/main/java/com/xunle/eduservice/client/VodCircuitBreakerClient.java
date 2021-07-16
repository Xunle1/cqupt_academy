package com.xunle.eduservice.client;

import com.xunle.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xunle
 */
@Component
public class VodCircuitBreakerClient implements VodClient{
    @Override
    public R removeAlyVideo(String videoId) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R removeBatchAlyVideo(List<String> videoList) {
        return R.error().message("删除多个视频出错了");
    }
}
