package com.xunle.eduservice.service;

import com.xunle.eduservice.entity.course.*;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoVo courseInfoVo);

    void updateCourse(CourseInfoVo courseInfoVo);

    PublishCourseVo getPublishCourseInfo(String courseId);

    void removeCourse(String courseId);

    ResultPageCourseInfo pageConditionQuery(Long current, Long limit, CourseQuery courseQuery);
}
