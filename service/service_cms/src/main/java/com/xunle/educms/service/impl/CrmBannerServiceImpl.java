package com.xunle.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xunle.educms.entity.CrmBanner;
import com.xunle.educms.mapper.CrmBannerMapper;
import com.xunle.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author xunle
 * @since 2021-06-19
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(key = "'selectIndexList'", value = "banner")
    @Override
    public List<CrmBanner> seleteAllBanners() {

        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        //根据id降序
        wrapper.orderByDesc("id");
        //拼接sql语句
        wrapper.last("limit 2");

        List<CrmBanner> list = baseMapper.selectList(wrapper);

        return list;
    }
}
