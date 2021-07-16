package com.xunle.eduucenter.service;

import com.xunle.eduucenter.entity.RegisterVo;
import com.xunle.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author xunle
 * @since 2021-06-20
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    boolean register(RegisterVo registerVo);
}
