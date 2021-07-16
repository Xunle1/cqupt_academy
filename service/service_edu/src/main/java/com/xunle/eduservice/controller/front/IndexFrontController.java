package com.xunle.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.commonutils.R;
import com.xunle.eduservice.entity.course.EduCourse;
import com.xunle.eduservice.entity.teacher.EduTeacher;
import com.xunle.eduservice.service.EduCourseService;
import com.xunle.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xunle
 */
@RestController
@RequestMapping("/eduservice/index")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前八条热门课程，前四条讲师
    @GetMapping("/list")
    public R index() {
        //查询前八条课程记录
        QueryWrapper<EduCourse> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByDesc("view_count");
        courseQueryWrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(courseQueryWrapper);

        //查询前四条教师记录
        QueryWrapper<EduTeacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByDesc("gmt_create");
        teacherQueryWrapper.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(teacherQueryWrapper);


        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
