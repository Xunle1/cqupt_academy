package com.xunle.edumsm.controller;

import com.xunle.commonutils.R;
import com.xunle.edumsm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xunle
 */
@RestController
@RequestMapping("/edumsm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @PostMapping("/send/{email}")
    public R sendEmail(@PathVariable String email) {
        msmService.sendEmail(email);

        return R.ok();
    }
}
