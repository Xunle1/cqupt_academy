package com.xunle.eduservice.entity.course;

import lombok.Data;

import java.util.List;

/**
 * @author xunle
 * 封装课程分页条件查询结果
 */
@Data
public class ResultPageCourseInfo {

    private Long total;
    private List<EduCourse> records;
}
