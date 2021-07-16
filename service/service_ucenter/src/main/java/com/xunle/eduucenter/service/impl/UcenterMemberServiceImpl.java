package com.xunle.eduucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.commonutils.JwtUtils;
import com.xunle.commonutils.MD5;
import com.xunle.eduucenter.entity.RegisterVo;
import com.xunle.eduucenter.entity.UcenterMember;
import com.xunle.eduucenter.mapper.UcenterMemberMapper;
import com.xunle.eduucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xunle.servicebase.handler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author xunle
 * @since 2021-06-20
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public String login(UcenterMember member) {
        //获取邮箱和密码
        String email = member.getEmail();
        String pwd = member.getPassword();

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(pwd)) {
            throw new GuliException(20001, "登陆失败");
        }

        //判断邮箱
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if (null == ucenterMember) {
            throw new GuliException(20001, "不存在该邮箱");
        }

        //判断密码
        //MD5解密
        if (!MD5.encrypt(pwd).equals(ucenterMember.getPassword())) {
            throw new GuliException(20001, "密码错误");
        }

        //判断用户是否被禁用
        if (ucenterMember.getIsDisabled()) {
            throw new GuliException(20001, "登陆失败");
        }

        String jwtToken = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        return jwtToken;
    }

    @Override
    public boolean register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String email = registerVo.getEmail();
        String nickName = registerVo.getNickName();
        String pwd = registerVo.getPassword();

        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(pwd) || StringUtils.isEmpty(nickName) || StringUtils.isEmpty(code)) {
            System.out.println("service" + registerVo);
            return false;
//            throw new GuliException(20001, "邮箱，密码，验证码不能为空");
        }

        //判断验证码
        String verifyCode = redisTemplate.opsForValue().get(email);
        if (!code.equals(verifyCode)) {
            return false;
            //throw new GuliException(20001, "验证码出错，注册失败");
        }

        //判断邮箱是否存在
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("email",email);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            return false;
//            throw new GuliException(20001, "邮箱已存在");
        }

        UcenterMember member = new UcenterMember();
        member.setEmail(email);
        member.setPassword(MD5.encrypt(pwd));
        member.setNickname(nickName);

        baseMapper.insert(member);
        return true;
    }
}
