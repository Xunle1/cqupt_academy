package com.xunle.servicebase.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xunle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException{
    //状态码
    private Integer code;
    //异常信息
    private String message;
}
