package com.xunle.eduservice.controller;


import com.xunle.commonutils.R;
import com.xunle.eduservice.client.VodClient;
import com.xunle.eduservice.entity.chapter.EduVideo;
import com.xunle.eduservice.service.EduVideoService;
import com.xunle.servicebase.handler.GuliException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
@Api(tags = "课时管理")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient client;

    //添加课时
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo video) {
        videoService.save(video);

        return R.ok();
    }

    //删除课节
    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id) {

        //根据小节id获取videoId
        EduVideo video = videoService.getById(id);
        String videoId = video.getVideoSourceId();

        if (!StringUtils.isEmpty(videoId)) {
            R r = client.removeAlyVideo(videoId);
            if (r.getCode() == 20001) {
                throw new GuliException(20001, "删除视频失败，熔断器");
            }
        }

        boolean flag = videoService.removeById(id);
        if (flag) {
            return R.ok();
        }

        return R.error();
    }
    //修改小节 TODO
}

