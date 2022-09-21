package com.geekbrains.spring.web.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class JwtRequest {
    @Schema(description = "Имя пользователя")
    private String username;
    @Schema(description = "Пароль")
    private String password;
}
