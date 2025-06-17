package com.amaris.login.authapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Map;

@FeignClient(name = "dummyJsonUsers", url = "${dummyjson.api.url}")
public interface DummyJsonUsersClient {
    @GetMapping("/users")
    Map<String, Object> getUsers();
}
