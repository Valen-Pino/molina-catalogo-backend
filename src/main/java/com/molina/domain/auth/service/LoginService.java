package com.molina.domain.auth.service;

import com.molina.domain.auth.dto.LoginDTO;
import com.molina.domain.auth.entity.Usuario;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class LoginService {

    public String autenticar(LoginDTO dto) {

        Usuario usuarioEntity = Usuario.find("username", dto.usuario()).firstResult();

        if (usuarioEntity != null && BcryptUtil.matches(dto.password(), usuarioEntity.getPassword())) {

            return Jwt.issuer("https://molina-catalogo.com")
                    .upn(usuarioEntity.getUsername())
                    .groups("Admin")
                    .expiresIn(Duration.ofHours(8))
                    .sign();
        }

        throw new IllegalArgumentException("Credenciales inválidas");

    }

}