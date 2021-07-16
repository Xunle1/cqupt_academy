package com.xunle.eduservice.service;

import com.xunle.eduservice.entity.chapter.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String courseId);
}
