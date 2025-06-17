package com.amaris.login.authapi.service;

import com.amaris.login.authapi.feign.DummyJsonUsersClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UsersService {
    @Autowired
    private DummyJsonUsersClient dummyJsonUsersClient;

    public Map<String, Object> getUsers() {
        return dummyJsonUsersClient.getUsers();
    }
}
