package org.cc.demon.axon.orderexample.commandmodel;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.cc.demon.axon.orderexample.domain.order.CreateOrderCommand;
import org.cc.demon.axon.orderexample.domain.order.Order;
import org.cc.demon.axon.orderexample.domain.order.OrderCreatedEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class OrderTest {
    private AggregateTestFixture<Order> testFixture;

    @Before
    public void setUp() {
        this.testFixture = new AggregateTestFixture<>(Order.class);
    }

    @Test
    public void testCreateOrder() {
        String orderId = UUID.randomUUID().toString();
        String productId = UUID.randomUUID().toString();
        testFixture.givenNoPriorActivity()
                .when(new CreateOrderCommand(orderId, productId, 10))
                .expectEvents(new OrderCreatedEvent(orderId, productId, 10));
    }
}
