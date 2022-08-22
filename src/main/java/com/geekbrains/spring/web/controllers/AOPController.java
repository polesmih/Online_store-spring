package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.services.AOPService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistic")
public class AOPController {

    private final AOPService statistic;

    @GetMapping
    public Map<String, Long> getServiceStatistics() {
        return statistic.getServiceDurations();
    }
}
