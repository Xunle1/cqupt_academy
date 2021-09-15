package com.xunle.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunle.eduservice.entity.course.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xunle.eduservice.entity.teacher.EduTeacher;

import java.util.Map;

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

    Map<String, Object> getCourses(Page<EduCourse> coursePage, CourseFrontVO courseFrontVO);
}
