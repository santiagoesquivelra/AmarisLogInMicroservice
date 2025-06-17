package com.amaris.login.authapi.repository;

import com.amaris.login.authapi.model.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
}
