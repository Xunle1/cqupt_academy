package com.xunle.eduservice.entity.course;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xunle
 */
@Data
@ApiModel(value = "课程信息发布对象")
public class PublishCourseVo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
