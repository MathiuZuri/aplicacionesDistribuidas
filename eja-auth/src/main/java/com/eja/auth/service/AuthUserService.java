package com.eja.auth.service;


import com.eja.auth.dto.AuthUserDto;
import com.eja.auth.entity.AuthUser;
import com.eja.auth.entity.TokenDto;

public interface AuthUserService {
    public AuthUser save(AuthUserDto authUserDto);


    public TokenDto login(AuthUserDto authUserDto);


    public TokenDto validate(String token);
}

