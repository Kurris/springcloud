package com.example.common.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CurrentUserInfo {
    private Long userId;
    private String username;
    private String name;
    private Long tenantId;
    private Long orgId;
    private String phone;
    private String email;
}
