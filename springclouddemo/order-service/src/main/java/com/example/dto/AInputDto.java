package com.example.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AInputDto {

    @NotBlank(message = "name不能为空")
    private String name;
}
