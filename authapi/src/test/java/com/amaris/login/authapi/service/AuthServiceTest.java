package com.amaris.login.authapi.service;

import com.amaris.login.authapi.feign.DummyJsonAuthClient;
import com.amaris.login.authapi.model.LoginLog;
import com.amaris.login.authapi.repository.LoginLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {
    @Mock
    private DummyJsonAuthClient dummyJsonAuthClient;
    @Mock
    private LoginLogRepository loginLogRepository;
    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_successful_shouldRegisterLoginLog() {
        String username = "emilys";
        String password = "emilyspass";
        Map<String, Object> dummyResponse = new HashMap<>();
        dummyResponse.put("accessToken", "access-token-123");
        dummyResponse.put("refreshToken", "refresh-token-456");
        when(dummyJsonAuthClient.login(any())).thenReturn(dummyResponse);

        Map<String, Object> result = authService.login(username, password);

        assertEquals("access-token-123", result.get("accessToken"));
        ArgumentCaptor<LoginLog> captor = ArgumentCaptor.forClass(LoginLog.class);
        verify(loginLogRepository, times(1)).save(captor.capture());
        LoginLog log = captor.getValue();
        assertEquals(username, log.getUsername());
        assertEquals("access-token-123", log.getAccessToken());
        assertEquals("refresh-token-456", log.getRefreshToken());
        assertNotNull(log.getLoginTime());
    }
}
