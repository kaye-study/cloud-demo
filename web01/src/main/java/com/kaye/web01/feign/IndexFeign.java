package com.kaye.web01.feign;

import com.kaye.web01.feign.impl.IndexFallBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * java类作用描述
 *
 * @author yk
 * @since 2019/2/1$ 10:39$
 */
@FeignClient(value = "consumer", fallback = IndexFallBack.class)
public interface IndexFeign {

    @RequestMapping(value = "/consumer")
    String get();

}
