package com.example.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.clients.TicketClient;
import com.example.common.annontaion.Authorize;
import com.example.common.dto.ApiResult;
import com.example.common.dto.CurrentUserInfo;
import com.example.domain.dto.AInputDto;
import com.example.domain.entity.TableNameEntity;
import com.example.mapper.TableNameMapper;
import com.example.service.TableNameService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class OrderController {

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private CurrentUserInfo currentUserInfo;

    @Autowired
    private TableNameService tableNameService;

    @Autowired
    private TableNameMapper tableNameMapper;


    @Autowired
    private TicketClient ticketClient;

    //    @Authorize
    @GetMapping("/getName")
    public TableNameEntity getName(@Validated AInputDto input) {

        ApiResult<String> nihao = ticketClient.getName("nihao");

        //tableNameMapper.deleteById(1);
        Page<TableNameEntity> tableNameEntityPage = tableNameMapper.selectPage(new Page<>(2, 2), Wrappers.lambdaQuery());
        List<TableNameEntity> records = tableNameEntityPage.getRecords();
        long total = tableNameEntityPage.getTotal();
        long pages = tableNameEntityPage.getPages();

        TableNameEntity tableNameEntity = tableNameMapper.selectById(1);
        return tableNameEntity;
    }
}
