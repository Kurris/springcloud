package com.example.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AInputDto {

    private Integer id;

    @Schema(description = "名称")
    @NotBlank(message = "name不能为空")
    private String name;

    private String jsonCol;
}
