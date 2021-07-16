package com.xunle.educms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunle.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author xunle
 * @since 2021-06-19
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> seleteAllBanners();
}
