package com.kaye.getaway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * 路由多虑器
 * 1.为了实现对客户端请求的安全校验和权限控制
 *
 * @author yk
 * @since 2019/2/1$ 16:25$
 */
public class AccessFilter02 extends ZuulFilter {

    private static final Logger log = LoggerFactory.getLogger(AccessFilter02.class);

    /**
     * 配置在什么时候过滤
     * <p>
     * pre：     可以在请求被路由之前调用
     * routing： 路由请求时被调用
     * post：    在routing和error过滤器之后被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 当有多个过滤器的时候
     * 通过int值来定义过滤器的执行顺序
     * 数值越小优先级越高。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * 判断是否需要使用该过滤器
     * 可以通过一些业务逻辑来判断
     * 我们可以通过此方法来指定过滤器的有效范围
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //获取请求
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            return true;
        }
        return Integer.valueOf(accessToken) > 10;
    }

    /**
     * 过滤的具体执行逻辑
     *
     * @return
     */
    @Override
    public Object run() {
        //获取请求
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
        //如果请求里没有accessToken参数那么就拒绝它访问
        Object accessToken = request.getParameter("accessToken");
        //意思是只要访问码不是4311就会被拦截，请求就不会走路由
        if (!"4311".equals(accessToken)) {
            log.warn("access token is empty");
            //设置是否响应回去
            ctx.setSendZuulResponse(true);
            //设置相应头
            ctx.getResponse().setHeader("Content-type", "text/html;charset=UTF-8");
            //设置相应体 多个多虑器时会被最后一个的内容覆盖
            ctx.setResponseBody("filter02's content is 123456");
            //设置相应类型
            ctx.getResponse().setContentType("application/json");
            //设置请求码
            ctx.setResponseStatusCode(200);
            return null;
        }
        log.info("access token ok");
        return null;
    }
}
