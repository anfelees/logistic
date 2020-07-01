package com.hiberus.logistic.service.imp;

import com.hiberus.logistic.model.OrderModel;
import com.hiberus.logistic.repository.OrderRepository;
import com.hiberus.logistic.service.OrderService;
import com.hiberus.logistic.vo.BasicResponse;
import com.hiberus.logistic.vo.FullOrder;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.time.LocalDateTime;

/**
 * OrderService default implementation
 */
@Service
public class OrderServiceImp implements OrderService {

    @Setter
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Mono<BasicResponse> sendOrder(Mono<FullOrder> orderSignal) {
        return Mono.create(sink -> {
            orderSignal.doOnSuccess(order -> {
                processOrder(sink, order);
            }).doOnError(err -> {
                sink.error(err);
            }).subscribe();
        });
    }

    private void processOrder(MonoSink<BasicResponse> sink, FullOrder order) {
        OrderModel model = new OrderModel();
        model.setCheckoutOrderId(order.getOrderId());
        model.setClientId(order.getClientId());
        model.setDirection(order.getDirection());
        model.setTotalAmount(order.getTotalOrderAmount());
        model.setCreated(LocalDateTime.now());
        orderRepository.save(model)
                .doOnError(sink::error)
                .doOnSuccess(orderModel -> sink.success(new BasicResponse(true, "ok")))
                .subscribe();
    }
}
