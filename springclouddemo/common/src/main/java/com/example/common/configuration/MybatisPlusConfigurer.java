package com.example.common.configuration;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.example.common.database.dto.abstraction.ITenantEntity;
import com.example.common.dto.CurrentUserInfo;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfigurer {

//    @Autowired(required = false)
//    private CurrentUserInfo currentUserInfo;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

//        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new TenantLineHandler() {
//
//            @Override
//            public String getTenantIdColumn() {
//                return TenantLineHandler.super.getTenantIdColumn();
//            }
//
//            @Override
//            public Expression getTenantId() {
//                if (currentUserInfo == null) {
//                    return new LongValue(-1);
//                }
//                if (currentUserInfo.getTenantId() == null) {
//                    return new LongValue(-1);
//                }
//                return new LongValue(currentUserInfo.getTenantId());
//            }
//        }));

        return interceptor;
    }
}
