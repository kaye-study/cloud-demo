package com.kaye.eureka.listener;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * 监听服务上线下，注册，服务断线，续约等信息
 *
 * @author yukai
 * @since 2018-12-25 09:36
 */
@Component
public class EurekaStateChangeListener {

    private static final Logger logger = LoggerFactory.getLogger(EurekaStateChangeListener.class);

    /**
     * 处理服务断线事件
     *
     * @param eurekaInstanceCanceledEvent 服务断线事件
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent) {
        String appName = eurekaInstanceCanceledEvent.getAppName();
        String serverId = eurekaInstanceCanceledEvent.getServerId();
        logger.info("---------服务断线事件，服务名为:{}-----服务id：{}", appName, serverId);
    }

    /**
     * 处理服务注册事件
     *
     * @param event 服务注册事件
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        logger.info("---------服务注册事件，服务名为：{}，服务组名:{}", instanceInfo.getAppName(), instanceInfo.getAppGroupName());
    }

    /**
     * 处理服务续约事件
     *
     * @param event 服务续约事件
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        logger.info("---------服务续约事件，服务名为：{}，服务id为：{}", event.getAppName(), event.getServerId());
    }

    /**
     * 处理注册中心启动事件
     *
     * @param event 注册中心启动事件
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        logger.info("---------Eureka注册中心启动事件，{}", event.toString());
    }

    /**
     * 处理Eureka Server启动事件
     *
     * @param event Eureka Server启动事件
     */
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        logger.info("---------Eureka Server启动事件，{}", event.toString());
    }


}
