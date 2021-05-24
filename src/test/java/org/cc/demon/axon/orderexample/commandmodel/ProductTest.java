package org.cc.demon.axon.orderexample.commandmodel;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.cc.demon.axon.orderexample.domain.product.*;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class ProductTest {
    AggregateTestFixture<Product> testFixture;

    @Before
    public void setUp() {
        testFixture = new AggregateTestFixture<>(Product.class);
    }

    @Test
    public void testCreateProduct() {
        String id = UUID.randomUUID().toString();

        testFixture.givenNoPriorActivity()
                .when(new AddProductCommand(id, 100, 10.0,"product name", "Some decription"))
                .expectEvents(new ProductAddedEvent(id, 100,10.0, "product name", "Some decription"));
    }

    @Test
    public void testDecreaseProductCount() {
        String id = UUID.randomUUID().toString();

        testFixture.given(new ProductAddedEvent(id, 100, 10.0, "product name", "Some decription"))
                .when(new DecreaseAmountCommand(id, 50))
                .expectEvents(new AmountDecreasedEvent(id, 50));
    }

    @Test
    public void testDecreaseProductCountNotEnough() {
        String id = UUID.randomUUID().toString();

        testFixture.given(new ProductAddedEvent(id, 100, 10.0, "product name", "Some decription"))
                .when(new DecreaseAmountCommand(id, 150))
                .expectException(RuntimeException.class)
                .expectExceptionMessage("库存不足");
    }
}

