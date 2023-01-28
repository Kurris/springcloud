package com.example.common.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.example.common.database.dto.BaseEntity;
import com.example.common.database.dto.abstraction.ITenantEntity;

import java.util.Collections;

/**
 * <a href="https://baomidou.com/pages/981406/">https://baomidou.com/pages/981406/</a>
 */
public class MPGenerator {

    /**
     * 逆向工程
     */
    public static void main(String[] args) {

        FastAutoGenerator.create("jdbc:mysql://ip:port/database", "root", "password")
                .globalConfig(builder -> {
                    builder.author("ligy")
                            .dateType(DateType.ONLY_DATE)
                            .disableOpenDir()
                            .outputDir(System.getProperty("user.dir") + "//tmp"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("data")
                            .entity("domain.entity")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "//tmp//mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    StrategyConfig build = builder
//                            .addInclude("t_app_table_task") // 设置需要生成的表名,注释掉为全部
//                            .addTablePrefix("t_app")
                            .build();// 设置过滤表前缀
                    // todo 注意 moduleName , superClass 的修改
                    build.serviceBuilder().formatServiceFileName("%sService").build();
                    // todo 公共字段实体  FieldEntity.class FieldTenantEntity.class
                    build.entityBuilder().formatFileName("%s").superClass(BaseEntity.class).addSuperEntityColumns(String.valueOf(ITenantEntity.tenantId))
                            .build();
                    // todo 生成字段映射
//                    build.mapperBuilder().enableBaseResultMap().build();
                })
                .templateConfig(builder -> {
                    builder.controller("/template/controller.java.vm");
                    builder.entity("/template/entity.java.vm");
                    builder.service("/template/service.java.vm");
                    builder.serviceImpl("/template/serviceImpl.java.vm");
                    builder.mapper("/template/mapper.java.vm");
                    builder.xml("/template/mapper.xml.vm");
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}

