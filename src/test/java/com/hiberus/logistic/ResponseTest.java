package com.hiberus.logistic;

import com.hiberus.logistic.model.OrderModel;
import com.hiberus.logistic.repository.OrderRepository;
import com.hiberus.logistic.service.imp.OrderServiceImp;
import com.hiberus.logistic.vo.BasicResponse;
import com.hiberus.logistic.vo.FullOrder;
import com.hiberus.logistic.vo.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ResponseTest {

    @MockBean
    OrderRepository orderRepository;
    @Autowired
    OrderServiceImp orderService;

    @Test
    public void whenConnectionToDbFail() {
        orderService.setOrderRepository(orderRepository);
        when(this.orderRepository.save(MockitoHamcrest.argThat(hasProperty("direction", is("fail")))))
                .thenThrow(new DataAccessResourceFailureException("Failed to obtain R2DBC Connection"));
        FullOrder order = createOrder();
        order.setDirection("fail");
        Mono<BasicResponse> mono = this.orderService.sendOrder(Mono.just(order));
        StepVerifier.create(mono).expectError(DataAccessResourceFailureException.class);
    }

    @Test
    public void happyPath() {
        doReturn(Mono.just(createOrderModel())).when(this.orderRepository)
                .save(MockitoHamcrest.argThat(hasProperty("direction", is("ok"))));
        FullOrder order = createOrder();
        order.setDirection("ok");
        orderService.sendOrder(Mono.just(order))
                .doOnSuccess(bs -> {
                    assertEquals(bs.getMessage(), "feafef");
                    assertTrue(bs.isSuccess());
                }).doOnError(err -> fail())
                .subscribe();
    }

    private OrderModel createOrderModel() {
        OrderModel model = new OrderModel();
        model.setClientId(1);
        model.setTotalAmount(BigDecimal.TEN);
        model.setDirection("ok");
        model.setCheckoutOrderId(1);
        model.setId(1L);
        return model;
    }

    private FullOrder createOrder() {
        FullOrder order = new FullOrder();
        order.setOrderId(1);
        order.setTotalOrderAmount(BigDecimal.TEN);
        order.setClientId(1);
        order.setDate(LocalDateTime.now());
        order.setDirection("local");
        List<Product> productList = new ArrayList<>();
        productList.add(createProduct());
        order.setProducts(productList);
        return order;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setId(1);
        product.setQuantity(1);
        product.setCost(BigDecimal.TEN);
        return product;
    }

    @Configuration
    @Import(OrderServiceImp.class)
    static class Config {
    }

}
