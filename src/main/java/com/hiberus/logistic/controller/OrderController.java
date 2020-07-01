package com.hiberus.logistic.controller;

import com.hiberus.logistic.service.OrderService;
import com.hiberus.logistic.vo.BasicResponse;
import com.hiberus.logistic.vo.FullOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Class that handles logistics operations on orders
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * Send and order
     * @param orderSignal order
     * @return operation status
     */
    @PostMapping
    public Mono<BasicResponse> sendOrder(@Valid @RequestBody Mono<FullOrder> orderSignal) {
        return orderService.sendOrder(orderSignal);
    }
}
