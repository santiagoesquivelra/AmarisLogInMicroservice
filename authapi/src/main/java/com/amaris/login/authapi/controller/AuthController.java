package com.amaris.login.authapi.controller;

import com.amaris.login.authapi.service.AuthService;
import com.amaris.login.authapi.service.MeService;
import com.amaris.login.authapi.service.UsersService;
import com.amaris.login.authapi.repository.LoginLogRepository;
import com.amaris.login.authapi.model.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador principal para la autenticación y gestión de usuarios.
 * Provee endpoints para login, consulta de usuario autenticado, usuarios de prueba y logs.
 *
 * @author Santiago Esquivel
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private MeService meService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private LoginLogRepository loginLogRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        Map<String, Object> response = authService.login(username, password);
        if (response.get("token") != null || response.get("accessToken") != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body(response);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(@RequestHeader("Authorization") String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String accessToken = bearerToken.substring(7);
            return ResponseEntity.ok(meService.getMe(accessToken));
        } else {
            return ResponseEntity.status(401).body("Missing or invalid Authorization header");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(usersService.getUsers());
    }

    @GetMapping("/logs")
    public ResponseEntity<?> getLogs() {
        return ResponseEntity.ok(loginLogRepository.findAll());
    }

    @PostMapping("/bearer-token")
    public ResponseEntity<?> getBearerToken(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        Map<String, Object> response = authService.login(username, password);
        String token = (String) response.get("token");
        if (token != null) {
            return ResponseEntity.ok(Map.of("bearer", "Bearer " + token));
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
