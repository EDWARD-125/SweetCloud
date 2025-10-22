package com.sweetcloud.security.controller;

import com.sweetcloud.dto.ApiResponse;
import com.sweetcloud.security.JwtUtil;
import com.sweetcloud.security.dto.LoginRequest;
import com.sweetcloud.security.dto.LoginResponse;
import com.sweetcloud.security.dto.RegisterRequest;
import com.sweetcloud.security.model.Usuario;
import com.sweetcloud.security.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            // ✅ Autenticar sin guardar en variable (solo validar credenciales)
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), 
                            request.getPassword()
                    )
            );
            
            String token = jwtUtil.generateToken(request.getUsername());
            LoginResponse response = new LoginResponse(token, request.getUsername());
            
            return ResponseEntity.ok(new ApiResponse("Login exitoso", response));
            
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("Credenciales inválidas", null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            Usuario usuario = usuarioService.registrarUsuario(
                    request.getUsername(), 
                    request.getPassword()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Usuario registrado exitosamente", usuario.getUsername()));
                    
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse("Error al registrar: " + e.getMessage(), null));
        }
    }
}