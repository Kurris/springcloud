//package com.example.demo;
//
//import com.alibaba.nacos.api.annotation.NacosInjected;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.api.naming.NamingService;
//import com.alibaba.nacos.api.naming.pojo.Instance;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PostConstruct;
//
//@Configuration
//public class NacosRegisterConfig {
//
//    @NacosInjected
//    private NamingService namingService;
//
//    @PostConstruct
//    public  void  registerInstance() throws NacosException {
//        String serviceName = "srv";
//        String groupName = "web";
//        Instance instance = new Instance();
//        instance.setIp("isawesome.cn");
//        instance.setPort(6001);
//        instance.setHealthy(true);
//        instance.setWeight(1.0);
//        namingService.registerInstance(serviceName, groupName, instance);
//    }
//}
