package com.example.domain.dto;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AInputDto {

    private Integer id;

    @NotBlank(message = "name不能为空")
    private String name;

    private String jsonCol;
}
