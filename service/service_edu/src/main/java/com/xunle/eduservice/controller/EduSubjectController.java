package com.xunle.eduservice.controller;


import com.xunle.commonutils.R;
import com.xunle.eduservice.entity.subject.OneSubject;
import com.xunle.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 课程分类 前端控制器
 * @author xunle
 * @since 2021-05-25
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
@Api(tags = "课程分类管理")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file) {
        //上传excel文件
        eduSubjectService.saveSubject(file, eduSubjectService);

        return R.ok();
    }

    @GetMapping("/listSubject")
    public R listSubject() {
        List<OneSubject> list = eduSubjectService.getAllSubject();

        return R.ok().data("list",list);
    }
}

