package com.programmingtechie.apigateway.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyCloakConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, List<String>> realmAccess= source.getClaim("realm_access");
        if(realmAccess == null || realmAccess.isEmpty()) {
            return new ArrayList<>();
        }

        Collection<GrantedAuthority> roleColl = ((List<String>)  realmAccess.get("roles"))
                .stream()
                .map(role -> "ROLE_"+ role)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return roleColl;
    }
}
