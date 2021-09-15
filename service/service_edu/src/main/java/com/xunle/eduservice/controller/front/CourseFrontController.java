package com.xunle.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunle.commonutils.R;
import com.xunle.eduservice.entity.course.CourseFrontVO;
import com.xunle.eduservice.entity.course.EduCourse;
import com.xunle.eduservice.entity.teacher.EduTeacher;
import com.xunle.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author xunle
 * @date 2021/9/15 22:00
 */
@Api(tags = "前台课程列表")
@RestController
@RequestMapping("/eduservice/course_front")
@CrossOrigin
@EnableDiscoveryClient
@Slf4j
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @PostMapping("/courses/{page}/{limit}")
    public R getAllCourses(@PathVariable long page, @PathVariable long limit, @RequestBody CourseFrontVO courseFrontVO) {
        Page<EduCourse> coursePage = new Page<>(page, limit);

        Map<String, Object> courseMap = courseService.getCourses(coursePage, courseFrontVO);
        return R.ok().data(courseMap);
    }

}
