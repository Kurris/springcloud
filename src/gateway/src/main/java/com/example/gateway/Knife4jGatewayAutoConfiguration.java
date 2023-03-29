/*
 * Copyright 2017-2022 八一菜刀(xiaoymin@foxmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @since -gateway-spring-boot-starter v4.0.0
 * @author <a href="xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2022/12/03 15:41
 */
@Slf4j
@Configuration
public class Knife4jGatewayAutoConfiguration {

    /**
     * swagger默认的url后缀
     */
    private static final String SWAGGER_URL = "/v3/api-docs?group=";

    /**
     * nacos注册中心
     */
    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 网关应用名称
     */
    @Value("${spring.application.name}")
    private String gatewayServiceName;
    
    /**
     * 分组url
     */
    public static final String GATEWAY_SWAGGER_GROUP_URL = "/swagger-resources";
    
    @Bean
    public RouterFunction<ServerResponse> gatewaySwaggerRoute() {
        log.info("init gateway swagger resources.");
        return RouterFunctions.route().GET(GATEWAY_SWAGGER_GROUP_URL, request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(this.build())).build();
    }

    /**
     * 构建分组数据
     */
    public List<Map<String, String>> build() {
        List<Map<String, String>> dataMaps = new ArrayList<>();
        List<String> nacosServiceNameList = this.discoveryClient.getServices();

        if (nacosServiceNameList.size() > 0) {
            nacosServiceNameList.remove(this.gatewayServiceName);
            for (String instanceName : nacosServiceNameList) {
                // 拼接swagger访问url，样式为`/demo/v2/api-info?group=demo`，当网关调用这个接口时，会自动通过负载均衡寻找对应的主机
                String url = "/" + instanceName + SWAGGER_URL + instanceName;
                Map<String, String> routeMap = new LinkedHashMap<>();
                routeMap.put("name", instanceName);
                routeMap.put("url", url);
                String id = Base64.getEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
                routeMap.put("id", id);
                dataMaps.add(routeMap);
            }
        }
        return dataMaps;
    }
}