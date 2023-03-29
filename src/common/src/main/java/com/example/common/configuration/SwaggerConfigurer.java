package com.example.common.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigurer {

    @Bean
    public GroupedOpenApi defaultApi() {
        String[] paths = {"/**"};
        //扫描的包
        //String[] packagedToMatch = {"com.xiaominfo.knife4j.demo.web"};
        return GroupedOpenApi.builder()
                .group("default")
                .pathsToMatch(paths)
                .addOperationCustomizer((operation, handlerMethod) -> operation
                        .addParametersItem(new HeaderParameter()
                                .name("自定义请求头Key")
                                .example("例子")
                                .description("自定义参数描述")
                                .schema(new StringSchema()
                                        ._default("默认值")
                                        .name("自定义请求头Key")
                                        .description("描述")
                                )
                        ))
                //.packagesToScan(packagedToMatch)
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("系统API文档")
                        .version("V1.0.0")
                        .description("系统API文档"))
                .externalDocs(new ExternalDocumentation())
                .addSecurityItem(new SecurityRequirement().addList("Authorization"))
                .components(new Components().addSecuritySchemes("Authorization",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER))
                        .addSecuritySchemes("Authorization", new SecurityScheme().type(SecurityScheme.Type.HTTP)));
    }
}
