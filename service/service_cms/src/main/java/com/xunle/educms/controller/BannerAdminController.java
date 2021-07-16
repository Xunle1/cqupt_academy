package com.xunle.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xunle.commonutils.R;
import com.xunle.educms.entity.CrmBanner;
import com.xunle.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台管理banner接口
 * </p>
 *
 * @author xunle
 * @since 2021-06-19
 */
@Api(tags = "首页Banner管理")
@RestController
@RequestMapping("/educms/adminbanner")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;

    //分页查询Banner
    @GetMapping("pageBanner/{current}/{limit}")
    public R getBannerList(@PathVariable Long current, @PathVariable Long limit) {
        Page<CrmBanner> pageParam = new Page<>(current, limit);
        bannerService.page(pageParam,null);

        return R.ok().data("item",pageParam.getRecords()).data("total",pageParam.getTotal());
    }

    //根据id查询Banner
    @GetMapping("getbanner/{id}")
    public R getBanner(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);

        return R.ok().data("item",banner);
    }

    //添加Banner
    @PostMapping("addbanner")
    public R addBanner(@RequestBody CrmBanner banner) {
        bannerService.save(banner);

        return R.ok();
    }

    //修改Banner
    @PutMapping("updatebanner")
    public R updateBanner(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);

        return R.ok();
    }

    //删除Banner
    @DeleteMapping("deletebanner/{id}")
    public R deleteBanner(@PathVariable String id) {
        bannerService.removeById(id);

        return R.ok();
    }
}

