package com.amaris.login.authapi.service;

import com.amaris.login.authapi.feign.DummyJsonAuthClient;
import com.amaris.login.authapi.model.LoginLog;
import com.amaris.login.authapi.repository.LoginLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private DummyJsonAuthClient dummyJsonAuthClient;
    @Autowired
    private LoginLogRepository loginLogRepository;

    @Transactional
    public Map<String, Object> login(String username, String password) {
        Map<String, String> credentials = Map.of("username", username, "password", password);
        Map<String, Object> response = dummyJsonAuthClient.login(credentials);
        if (response == null || response.isEmpty()) {
            throw new RuntimeException("Login failed, no response from authentication service");
        }
        String accessToken = (String) response.get("accessToken");
        String refreshToken = (String) response.get("refreshToken");
        if (accessToken != null) {
            LoginLog LOGGER = new LoginLog();
            LOGGER.setUsername(username);
            LOGGER.setLoginTime(LocalDateTime.now());
            LOGGER.setAccessToken(accessToken);
            LOGGER.setRefreshToken(refreshToken);
            loginLogRepository.save(LOGGER);
        }
        return response;
    }
}
