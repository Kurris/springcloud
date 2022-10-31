package com.example.demo.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.example.demo.ApiResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "demo")
public class DemoController {

    @NacosValue(value = "${name}", autoRefreshed = true)
    private String name;

    @Autowired
    private Environment env;

    @Resource
    private RestTemplate restTemplate;

    @NacosInjected
    private NamingService namingService;

    @GetMapping(value = "get")
    public  String get(){
        return name;
    }

    @GetMapping(value = "getEnv")
    public  String getEnv(){
        return env.getProperty("name");
    }

    @GetMapping(value = "srvValue")
    public String srvValue(@RequestParam String type){

        return type;
    }


    @GetMapping(value = "getSrvValue")
    public Object getSrvValue(String type) throws NacosException, JsonProcessingException {
        String serviceName = "srv";
        String groupName = "web";
        String apiUrl = "/demo/srvValue";

        Instance instance = namingService.selectOneHealthyInstance(serviceName, groupName);
        String ip = instance.getIp();
        String port = String.valueOf(instance.getPort());

        String requesturl = "http://"+ip+":"+port +apiUrl;
        System.out.println(requesturl);
        Map<String, Object> map = new HashMap<>();
        map.put("type",type+"5001");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requesturl)
                .queryParam("type", type+"5001");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);

      String json =   response.getBody();
      return new ObjectMapper().readValue(json,ApiResult.class);
    }
}
