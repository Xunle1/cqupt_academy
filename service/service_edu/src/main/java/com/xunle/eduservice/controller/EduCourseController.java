package com.xunle.eduservice.controller;


import com.xunle.commonutils.R;
import com.xunle.eduservice.entity.course.*;
import com.xunle.eduservice.service.EduCourseDescriptionService;
import com.xunle.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xunle
 * @since 2021-05-31
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduCourseDescriptionService descriptionService;

    /* ============================添加课程=================================*/

    @PostMapping("/addcourse")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String courseId = eduCourseService.addCourseInfo(courseInfoVo);

        return R.ok().data("courseId", courseId);
    }

    // 根据id查询课程
    @GetMapping("/getcourse/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        EduCourse course = eduCourseService.getById(courseId);

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course,courseInfoVo);

        EduCourseDescription courseDescription = descriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return R.ok().data("course", courseInfoVo);
    }

    // 修改课程信息
    @PostMapping("/updatecourse")
    public R updateCourse(@RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateCourse(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程发布信息
    @GetMapping("/getPublishCourseInfo/{courseId}")
    public R getPublishCourseInfo(@PathVariable String courseId) {
        PublishCourseVo publishCourse = eduCourseService.getPublishCourseInfo(courseId);

        return R.ok().data("publishCourse",publishCourse);
    }

    //课程发布状态修改
    @PostMapping("/publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);

        return R.ok();
    }

    /* ============================课程列表=================================*/

    @GetMapping("/list")
    public R getCourseList() {
        List<EduCourse> list = eduCourseService.list(null);

        return R.ok().data("list", list);
    }

    @PostMapping("/pageConditionList/{current}/{limit}")
    public R pageConditionList(@PathVariable Long current,
                               @PathVariable Long limit,
                               @RequestBody(required = false) CourseQuery courseQuery) {

        ResultPageCourseInfo result = eduCourseService.pageConditionQuery(current, limit, courseQuery);

        return R.ok().data("total", result.getTotal()).data("rows", result.getRecords());
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        eduCourseService.removeCourse(courseId);

        return R.ok();
    }
}

