package com.hiberus.logistic;

import com.hiberus.logistic.vo.FullOrder;
import com.hiberus.logistic.vo.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
class InputValidationsTests {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void orderIdValidation() {
        FullOrder order = createOrder();
        order.setOrderId(0);
        Set<ConstraintViolation<FullOrder>> violations = validator.validate(order);
        assertEquals(1, violations.size());
        violations.forEach(action -> assertEquals("orderId is mandatory", action.getMessage()));
        order.setOrderId(2);
        violations = validator.validate(order);
        assertEquals(0, violations.size());
    }

    @Test
    void totalOrderAmountValidation() {
        FullOrder order = createOrder();
        order.setTotalOrderAmount(null);
        Set<ConstraintViolation<FullOrder>> violations = validator.validate(order);
        assertEquals(1, violations.size());
        violations.forEach(action -> assertEquals("totalOrderAmount is mandatory", action.getMessage()));
        order.setTotalOrderAmount(BigDecimal.ZERO);
        violations = validator.validate(order);
        assertEquals(1, violations.size());
        violations.forEach(action -> assertEquals("totalOrderAmount is mandatory", action.getMessage()));
        order.setTotalOrderAmount(BigDecimal.TEN);
        violations = validator.validate(order);
        assertEquals(0, violations.size());
    }

	@Test
	void clientIdValidation() {
		FullOrder order = createOrder();
		order.setClientId(0);
		Set<ConstraintViolation<FullOrder>> violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("clientId id is mandatory", action.getMessage()));
		order.setClientId(1);
		violations = validator.validate(order);
		assertEquals(0, violations.size());
	}

    @Test
	void dateValidation() {
		FullOrder order = createOrder();
		order.setDate(null);
		Set<ConstraintViolation<FullOrder>> violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("date is mandatory", action.getMessage()));
		order.setDate(LocalDateTime.now());
		violations = validator.validate(order);
		assertEquals(0, violations.size());
	}

	@Test
	void directionValidation() {
		FullOrder order = createOrder();
		order.setDirection(null);
		Set<ConstraintViolation<FullOrder>> violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("direction is mandatory", action.getMessage()));
		order.setDirection("");
		violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("direction is mandatory", action.getMessage()));
		order.setDirection(" ");
		violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("direction is mandatory", action.getMessage()));
		order.setDirection("local");
		violations = validator.validate(order);
		assertEquals(0, violations.size());
	}

	@Test
	void productsValidation() {
		FullOrder order = createOrder();
		order.setProducts(null);
		Set<ConstraintViolation<FullOrder>> violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("products is mandatory", action.getMessage()));
		List<Product> productList = new ArrayList<>();
		order.setProducts(productList);
		violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("products is mandatory", action.getMessage()));
		productList.add(createProduct());
		violations = validator.validate(order);
		assertEquals(0, violations.size());
	}

	@Test
	void productIdValidation() {
		FullOrder order = createOrder();
		order.getProducts().get(0).setId(0);
		Set<ConstraintViolation<FullOrder>> violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("Product id is mandatory", action.getMessage()));
		order.getProducts().get(0).setId(1);
		violations = validator.validate(order);
		assertEquals(0, violations.size());
	}

	@Test
	void quantityValidation() {
		FullOrder order = createOrder();
		order.getProducts().get(0).setQuantity(0);
		Set<ConstraintViolation<FullOrder>> violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("quantity is mandatory", action.getMessage()));
		order.getProducts().get(0).setQuantity(1);
		violations = validator.validate(order);
		assertEquals(0, violations.size());
	}

	@Test
	void costValidation() {
		FullOrder order = createOrder();
		order.getProducts().get(0).setCost(null);
		Set<ConstraintViolation<FullOrder>> violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("const is mandatory", action.getMessage()));
		order.getProducts().get(0).setCost(BigDecimal.ZERO);
		violations = validator.validate(order);
		assertEquals(1, violations.size());
		violations.forEach(action -> assertEquals("const is mandatory", action.getMessage()));
		order.getProducts().get(0).setCost(BigDecimal.TEN);
		violations = validator.validate(order);
		assertEquals(0, violations.size());
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
		return  product;
	}

}
