package com.hiberus.logistic.controller;

import com.hiberus.logistic.vo.ApiVersionMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Controller that handles the request used to check the application version
 */
@RestController
@RequestMapping("/")
public class ApiInfoController {

    @Value("${app.version}")
    private String version;

    /**
     * Path to check the application version
     * @return application version
     */
    @GetMapping("/api-version")
    public Mono<ApiVersionMessage> sendVersion(){
        return Mono.just(new ApiVersionMessage(version));
    }
}
