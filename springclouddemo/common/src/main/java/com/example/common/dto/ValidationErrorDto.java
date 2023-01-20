package com.example.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationErrorDto {
    private String objectName;
    private String field;
    private String message;
}
