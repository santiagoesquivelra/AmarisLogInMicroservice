package com.amaris.login.authapi.service;

import com.amaris.login.authapi.feign.DummyJsonAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MeService {
    @Autowired
    private DummyJsonAuthClient dummyJsonAuthClient;

    public Map<String, Object> getMe(String accessToken) {
        String cookie = "accessToken=" + accessToken;
        return dummyJsonAuthClient.getMe(cookie);
    }
}
