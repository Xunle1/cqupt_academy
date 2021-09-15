package com.xunle.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunle.eduservice.entity.teacher.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-05-11
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeachers(Page<EduTeacher> teacherPage);
}
