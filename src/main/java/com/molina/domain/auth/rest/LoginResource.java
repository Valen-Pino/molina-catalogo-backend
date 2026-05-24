package com.molina.domain.auth.rest;

import com.molina.domain.auth.dto.LoginDTO;
import com.molina.domain.auth.service.LoginService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    LoginService authService;

    @POST
    @Path("/login")
    public Response login(LoginDTO dto) {

        try {
            String token = authService.autenticar(dto);
            return Response.ok(Map.of("token", token)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(Map.of("error", e.getMessage()))
                    .build();
        }
    }

}