package com.example.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.api.IApiTicketService;
import com.example.common.dto.ApiResult;
import com.example.domain.dto.AInputDto;
import com.example.domain.entity.TableNameEntity;
import com.example.mapper.TableNameMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "订单服务")
@RestController
@RefreshScope
public class OrderController {

    @Autowired
    private TableNameMapper tableNameMapper;

    @Autowired
    private IApiTicketService ticketClient;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    //@Authorize
    @Operation(summary = "获取名称")
    @GetMapping("/getName")
    public TableNameEntity getName(@Validated AInputDto input) {

        ApiResult<String> nihao = ticketClient.getName("nihao");

        //tableNameMapper.deleteById(1);
        Page<TableNameEntity> tableNameEntityPage = tableNameMapper.selectPage(new Page<>(2, 2), Wrappers.lambdaQuery());
        List<TableNameEntity> records = tableNameEntityPage.getRecords();
        long total = tableNameEntityPage.getTotal();
        long pages = tableNameEntityPage.getPages();

        TableNameEntity tableNameEntity = tableNameMapper.selectById(1);
        rabbitTemplate.convertAndSend("ligy-queue", "nihao");
        return tableNameEntity;
    }
}
