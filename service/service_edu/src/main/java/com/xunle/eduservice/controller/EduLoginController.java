package com.xunle.eduservice.controller;

import com.xunle.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author xunle
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
@Api(tags = "登录")
public class EduLoginController {



    @PostMapping("login")
    public R login() {

        return R.ok().data("token","admin");
    }


    @GetMapping("info")
    public R info() {

        return R.ok()
                .data("roles","讲师")
                .data("name","xunle")
                .data("avatar","https://i.loli.net/2021/06/06/zOyC5XnFYkpKhE8.jpg");
    }
}
