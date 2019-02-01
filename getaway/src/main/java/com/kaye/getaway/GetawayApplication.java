package com.kaye.getaway;

import com.kaye.getaway.filter.AccessFilter01;
import com.kaye.getaway.filter.AccessFilter02;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @SpringCloudApplication包含了一下三个注解的功能
 * @SpringBootApplication
 * @EnableDiscoveryClient
 * @EnableCircuitBreaker
 */
@SpringCloudApplication
/**
 * 由于Spring Cloud Zuul在整合了Eureka之后，具备默认的服务路由功能，即：当我们这里构建的gateway应用启动并注册到eureka之后，服务网关
 * 会发现上面我们启动的两个服务web01和consumer，这时候Zuul就会创建两个路由规则。每个路由规则都包含两部分，一部分是外部请求的匹配规则，
 * 另一部分是路由的服务ID。针对当前示例的情况，Zuul会自动创建下面的两个路由规则：
 *
 * 转发到web01服务的请求规则为：/web01/**
 * 转发到consumer服务的请求规则为：/consumer/**
 * 最后，我们可以通过访问1101端口的服务网关来验证上述路由的正确性：
 *
 * 比如访问：http://localhost:50001/web01/index ，该请求将最终被路由到web01的/index接口上。
 */
@EnableZuulProxy
public class GetawayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetawayApplication.class, args);
    }

    /**
     * 实例化第一个过滤器
     *
     * @return
     */
    @Bean
    public AccessFilter01 accessFilter01() {
        return new AccessFilter01();
    }

    /**
     * 实例化第二个过滤器
     *
     * @return
     */
    @Bean
    public AccessFilter02 accessFilter02() {
        return new AccessFilter02();
    }

}

