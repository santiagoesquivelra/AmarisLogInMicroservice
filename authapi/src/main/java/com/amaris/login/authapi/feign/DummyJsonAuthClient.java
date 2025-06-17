package com.amaris.login.authapi.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.http.MediaType;
import java.util.Map;

@FeignClient(name = "dummyJsonAuth", url = "${dummyjson.api.url}")
public interface DummyJsonAuthClient {

    @PostMapping(value = "/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> login(@RequestBody Map<String, String> credentials);

    @GetMapping(value = "/auth/me", consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String, Object> getMe(@RequestHeader("Cookie") String accessTokenCookie);
}
