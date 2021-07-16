package com.xunle.eduservice.entity.teacher;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xunle
 */
@Data
public class TeacherQuery implements Serializable {

    @ApiModelProperty("讲师名称，模糊查询")
    private String name;

    @ApiModelProperty("讲师等级")
    private Integer level;

    /**
     * 使用String类型，前端传过来的数据无需进行类型转换
     */
    @ApiModelProperty(value = "查询开始时间", example = "2019-10-1 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2020-1-1 10:10:10")
    private String end;
}
