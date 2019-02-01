package com.kaye.web01.controller;

import com.kaye.web01.feign.IndexFeign;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * java类作用描述
 *
 * @author yk
 * @since 2019/2/1$ 9:49$
 */
@Controller
public class IndexController {

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private IndexFeign indexFeign;

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return indexFeign.get();
    }

}
