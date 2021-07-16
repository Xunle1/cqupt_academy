package com.xunle.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunle.commonutils.R;
import com.xunle.eduservice.entity.teacher.EduTeacher;
import com.xunle.eduservice.entity.teacher.TeacherQuery;
import com.xunle.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 讲师 前端控制器
 * @author xunle
 * @since 2021-05-11
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    /**
     * 注入service
     */
    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询所有讲师
     * @return 返回讲师集合
     */
    @GetMapping("findAll")
    @ApiOperation("查询所有讲师")
    public R findAll() {
        List<EduTeacher> list = teacherService.list(null);

        return R.ok().data("items",list);
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation("逻辑删除讲师")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation("分页查询讲师")
    public R pageListTeachers(@PathVariable Long current, @PathVariable Long limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        teacherService.page(page,null);

        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();

//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("rows",records);
//        return R.ok().data(map);
        return R.ok().data("total",total).data("rows",records);
    }

    @PostMapping("pageTeacherCondition/{current}/{limit}")
    @ApiOperation("条件及分页查询")
    public R pageTeacherCondition(@PathVariable Long current,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        //创建page对象
        Page<EduTeacher> page = new Page<>(current, limit);
        //创建查询条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断条件是否为空
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified",end);
        }

        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询及分页查询
        teacherService.page(page, wrapper);
        Long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("添加讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("/getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        EduTeacher teacher = teacherService.getById(id);
        if (null == teacher) {
            return R.error();
        } else {
            return R.ok().data("teacher",teacher);
        }
    }

    @ApiOperation("修改讲师")
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean update = teacherService.updateById(eduTeacher);
        if (update) {
            return R.ok().data("teacher",eduTeacher);
        } else {
            return R.error();
        }
    }
}

