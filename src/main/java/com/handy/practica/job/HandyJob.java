package com.handy.practica.job;

import com.handy.practica.service.HandySaleOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class HandyJob {

    private final HandySaleOrderService handySaleOrderService;

    @Scheduled(cron = "${handy.cron}")
    public void getInformationHandy() {
        log.info("Start JOB at {}", LocalDateTime.now());
        handySaleOrderService.get();
        log.info("Start JOB at {}", LocalDateTime.now());
    }
}
