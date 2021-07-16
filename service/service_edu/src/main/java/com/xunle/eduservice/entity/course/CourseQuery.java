package com.xunle.eduservice.entity.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xunle
 */
@Data
public class CourseQuery {

    @ApiModelProperty("课程名称，模糊查询")
    private String title;

    @ApiModelProperty("课程状态")
    private String status;
}
