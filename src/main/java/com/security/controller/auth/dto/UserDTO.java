package com.security.controller.auth.dto;

import com.security.controller.auth.model.AuthorityModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private List<AuthorityModel> authorityModels = new ArrayList<>();
    private String token;
}