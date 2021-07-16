package com.xunle.eduucenter.controller;


import com.xunle.commonutils.JwtUtils;
import com.xunle.commonutils.R;
import com.xunle.eduucenter.entity.RegisterVo;
import com.xunle.eduucenter.entity.UcenterMember;
import com.xunle.eduucenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author xunle
 * @since 2021-06-20
 */
@RestController
@RequestMapping("/ucenter")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("/login")
    public R loginUser(@RequestBody UcenterMember member) {

        //返回token值
        String token = memberService.login(member);

        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo) {
        System.out.println(registerVo);
        boolean flag = memberService.register(registerVo);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据token获取用户信息
    @GetMapping("/memberinfo")
    public R getMemberInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("memberInfo",member);
    }
}

