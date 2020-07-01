package com.hiberus.logistic.service;

import com.hiberus.logistic.vo.BasicResponse;
import com.hiberus.logistic.vo.FullOrder;
import reactor.core.publisher.Mono;

/**
 * Contract of a service that process logistic orders
 */
public interface OrderService {

    /**
     * Send and order
     * @param orderSignal order
     * @return operation status
     */
    Mono<BasicResponse> sendOrder(Mono<FullOrder> orderSignal);
}
