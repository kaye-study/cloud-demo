package com.kaye.web01.feign.impl;

import com.kaye.web01.feign.IndexFeign;
import org.springframework.stereotype.Component;

/**
 * java类作用描述
 *
 * @author yk
 * @since 2019/2/1$ 10:41$
 */
@Component
public class IndexFallBack implements IndexFeign {
    @Override
    public String get() {
        return "bad request";
    }
}
