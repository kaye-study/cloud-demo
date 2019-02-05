package com.kaye.consumer02.listener;

import com.netflix.discovery.DiscoveryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

/**
 * 用于监听服务正常上线下
 *
 * @author yk
 * @since 2019/1/3$ 10:46$
 */
@Component
public class ClientListener implements DisposableBean, ExitCodeGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientListener.class);

    /*应用监听器事件执行先后顺序如下：
    1. ApplicationStartingEvent
    2. ApplicationEnvironmentPreparedEvent
    3. ApplicationPreparedEvent
    4. ApplicationStartedEvent
    5. ApplicationReadyEvent
    6. ApplicationFailedEvent
    */

    /**
     * 当务服正常下线的时候调用
     * DiscoveryManager的方法让服务及时在Eureka中心下线
     * 该方法已过时，但暂时未找到替代的方法
     */
    @Override
    public void destroy() throws Exception {
        //TODO 关闭服务的时候实例出错  待解决
        LOGGER.info("aaa~~~,I was destroyed.");
        DiscoveryManager.getInstance().shutdownComponent();
    }

    @Override
    public int getExitCode() {
        return -2;
    }


}
