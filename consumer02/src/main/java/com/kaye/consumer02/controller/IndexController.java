package com.kaye.consumer02.controller;

import com.kaye.consumer02.feign.IndexFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * java类作用描述
 *
 * @author yk
 * @since 2019/2/1$ 9:49$
 */
@Controller
public class IndexController {

    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Resource
    public IndexFeign indexFeign;

    @GetMapping("/consumer")
    @ResponseBody
    public String dc() {
        return "------这个是consumer02";
    }

}
