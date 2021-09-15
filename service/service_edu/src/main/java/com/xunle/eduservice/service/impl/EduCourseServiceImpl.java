package com.xunle.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunle.eduservice.entity.course.*;
import com.xunle.eduservice.entity.teacher.EduTeacher;
import com.xunle.eduservice.mapper.EduCourseMapper;
import com.xunle.eduservice.service.EduChapterService;
import com.xunle.eduservice.service.EduCourseDescriptionService;
import com.xunle.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunle.eduservice.service.EduVideoService;
import com.xunle.servicebase.handler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;

    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {
        //1. 向课程表中添加数据
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);

        if (insert == 0) {
            //添加失败
            throw new GuliException(20001, "添加课程信息失败");
        }

        //2. 向课程简介表中添加课程简介
        String cid = eduCourse.getId();

        EduCourseDescription description = new EduCourseDescription();
        description.setId(cid);
        description.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.save(description);
        return cid;
    }

    @Override
    public void updateCourse(CourseInfoVo courseInfoVo) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, course);
        int i = baseMapper.updateById(course);
        if (i < 0) {
            throw new GuliException(20001, "修改课程信息失败");
        }

        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(description);
    }

    @Override
    public PublishCourseVo getPublishCourseInfo(String courseId) {
        PublishCourseVo publishCourseInfo = baseMapper.getPublishCourseInfo(courseId);

        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        //1.删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //2.删除章节
        eduChapterService.removeChapterByCourseId(courseId);

        //3.删除描述
        eduCourseDescriptionService.removeDescriptionByCourseId(courseId);

        //4.删除课程
        int result = baseMapper.deleteById(courseId);
        if (result == 0) {
            throw new GuliException(20001, "删除失败");
        }
    }

    @Override
    public ResultPageCourseInfo pageConditionQuery(Long current, Long limit, CourseQuery courseQuery) {

        //创建page对象
        Page<EduCourse> page = new Page<>(current, limit);

        //创建查询条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();

        //判断条件是否为空
        if (!StringUtils.isEmpty(title)) {
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }

        wrapper.orderByDesc("gmt_create");

        this.page(page,wrapper);
        Long total = page.getTotal();
        List<EduCourse> records = page.getRecords();

        ResultPageCourseInfo result = new ResultPageCourseInfo();
        result.setTotal(total);
        result.setRecords(records);

        return result;
    }

    @Override
    public Map<String, Object> getCourses(Page<EduCourse> coursePage, CourseFrontVO courseFrontVO) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String parentId = courseFrontVO.getSubjectParentId();
        String subjectId = courseFrontVO.getSubjectId();
        String buyCountSort = courseFrontVO.getBuyCountSort();
        String priceSort = courseFrontVO.getPriceSort();
        String gmtCreateSort = courseFrontVO.getGmtCreateSort();

        if (!StringUtils.isEmpty(parentId)) {
            wrapper.eq("subject_parent_id",parentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(buyCountSort)) {
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(priceSort)) {
            wrapper.orderByDesc("price");
        }
        if (!StringUtils.isEmpty(gmtCreateSort)) {
            wrapper.orderByDesc("gmt_create");
        }
        //将分页数据封装到coursePage
        baseMapper.selectPage(coursePage, wrapper);

        List<EduCourse> records = coursePage.getRecords();
        long current = coursePage.getCurrent();
        long pages = coursePage.getPages();
        long size = coursePage.getSize();
        long total = coursePage.getTotal();
        boolean hasNext = coursePage.hasNext();
        boolean hasPrevious = coursePage.hasPrevious();


        Map<String, Object> map = new HashMap<>();
        map.put("records",records);
        map.put("current",current);
        map.put("pages", pages);
        map.put("size",size);
        map.put("total",total);
        map.put("hasNext",hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }
}
