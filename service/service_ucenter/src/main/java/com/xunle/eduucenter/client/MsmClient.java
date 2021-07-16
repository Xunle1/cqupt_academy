package com.xunle.eduucenter.client;

import com.xunle.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xunle
 */
@Component
@FeignClient("service-msm")
public interface MsmClient {

    @PostMapping("/edumsm/send")
    R sendEmail(String email);
}
