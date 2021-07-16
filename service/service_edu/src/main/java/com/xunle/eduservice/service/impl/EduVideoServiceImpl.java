package com.xunle.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.eduservice.client.VodClient;
import com.xunle.eduservice.entity.chapter.EduVideo;
import com.xunle.eduservice.mapper.EduVideoMapper;
import com.xunle.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    private VodClient client;

    @Override
    public void removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.select("video_source_id");
        List<EduVideo> videoList = baseMapper.selectList(wrapper);

        //获取视频Id集合
        List<String> videoIdList = new ArrayList<>();
        for (int i = 0; i < videoList.size(); i++) {
            EduVideo eduVideo = videoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)) {
                //放到videoIds集合里面
                videoIdList.add(videoSourceId);
            }
        }
        if (videoIdList.size() > 0) {
            client.removeBatchAlyVideo(videoIdList);
        }
        QueryWrapper<EduVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        baseMapper.delete(queryWrapper);
    }
}
