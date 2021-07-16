package com.xunle.eduservice.mapper;

import com.xunle.eduservice.entity.course.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xunle.eduservice.entity.course.PublishCourseVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    PublishCourseVo getPublishCourseInfo(String courseId);
}
