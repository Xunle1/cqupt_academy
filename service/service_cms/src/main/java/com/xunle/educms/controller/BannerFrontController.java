package com.xunle.educms.controller;

import com.xunle.commonutils.R;
import com.xunle.educms.entity.CrmBanner;
import com.xunle.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台banner显示
 * @author xunle
 */
@RestController
@RequestMapping("/educms/frontbanner")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    @GetMapping("/getbanners")
    public R getAllBnners() {
        List<CrmBanner> list = bannerService.seleteAllBanners();

        return R.ok().data("list",list);
    }
}
