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
    private String avatar = "https://himg.bdimg.com/sys/portrait/item/public.1.3cae19ec.03mpVMyNST-7wSr6SWc6uQ.jpg";
}
