package com.xunle.eduservice.service;

import com.xunle.eduservice.entity.course.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    void removeDescriptionByCourseId(String courseId);
}
