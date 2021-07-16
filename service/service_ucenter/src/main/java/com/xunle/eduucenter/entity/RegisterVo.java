package com.xunle.eduucenter.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xunle
 */
@Data
@ApiModel(value = "注册对象", description = "注册对象")
public class RegisterVo {

    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("验证码")
    private String code;
}
