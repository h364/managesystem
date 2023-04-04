package com.hh.springboot.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    @Builder.Default
    private String nickname = "用户";
    @Builder.Default
    private String avatar = "http://localhost:9090/manage/file/01113e0a21734eddbb4e1a4058195313.jpg";
    private String token;
}
