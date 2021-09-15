package com.xunle.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunle.commonutils.R;
import com.xunle.eduservice.entity.course.EduCourse;
import com.xunle.eduservice.entity.teacher.EduTeacher;
import com.xunle.eduservice.service.EduCourseService;
import com.xunle.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xunle
 * @date 2021/9/15 20:20
 */
@Api(tags = "前台教师列表")
@RestController
@RequestMapping("/eduservice/teacher_front")
@CrossOrigin
@EnableDiscoveryClient
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //分页查询讲师
    @PostMapping("/teachers/{page}/{limit}")
    public R getAllTeachers(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> teacherPage = new Page<>(page, limit);
        Map<String, Object> teacherMap = teacherService.getTeachers(teacherPage);

        //返回分页所有数据

        return R.ok().data(teacherMap);
    }

    @GetMapping("/teacher/{teacherId}")
    public R getTeacher(@PathVariable String teacherId) {
        EduTeacher teacher = teacherService.getById(teacherId);

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> list = courseService.list(wrapper);

        return R.ok().data("teacher",teacher).data("list",list);
    }
}
