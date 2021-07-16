package com.xunle.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.eduservice.entity.course.EduCourseDescription;
import com.xunle.eduservice.mapper.EduCourseDescriptionMapper;
import com.xunle.eduservice.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {
    @Override
    public void removeDescriptionByCourseId(String courseId) {
        QueryWrapper<EduCourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("id",courseId);
        baseMapper.delete(wrapper);
    }
}
