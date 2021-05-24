package org.cc.demon.axon.orderexample.domain.order;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.cc.demon.axon.orderexample.domain.product.DecreaseAmountCommand;
import org.cc.demon.axon.orderexample.domain.product.Product;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderEventListener {
    private final OrderRepository orderJpaRepository;

    @Resource
    private Repository<Product> productRepository;

    public OrderEventListener(OrderRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent evt) {
        orderJpaRepository.save(new OrderView(evt.getId(), evt.getProductId(), evt.getAmount()));

        Aggregate<Product> productAggregate = productRepository.load(evt.getProductId().toString());
        productAggregate.execute(r -> r.on(new DecreaseAmountCommand(evt.getProductId(), evt.getAmount())));
    }
}
