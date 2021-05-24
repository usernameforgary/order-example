package org.cc.demon.axon.orderexample.domain.product;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.cc.demon.axon.orderexample.domain.order.CreateOrderCommand;

import java.util.UUID;

@Aggregate
public class Product {
    @AggregateIdentifier
    private String id;
    private Double price;
    private int amount;
    private String name;
    private String description;

    public Product() {
    }

    @CommandHandler
    public Product(AddProductCommand command) {
        ProductAddedEvent evt = new ProductAddedEvent(command.getId(), command.getAmount(), command.getPrice(), command.getName(), command.getDescription());
        AggregateLifecycle.apply(evt);
    }

    @EventSourcingHandler
    public void on(ProductAddedEvent evt) {
        this.id = evt.getId();
        this.amount = evt.getAmount();
        this.price = evt.getPrice();
        this.name = evt.getName();
        this.description = evt.getDescription();
    }

    @CommandHandler
    public void on(DecreaseAmountCommand cmd) {
        if(this.amount < cmd.getAmount()) {
            throw new RuntimeException("库存不足");
        }
        AggregateLifecycle.apply(new AmountDecreasedEvent(cmd.getProductId(), cmd.getAmount()));
    }

    @EventSourcingHandler
    public void on(AmountDecreasedEvent evt) {
        this.amount = this.amount - evt.getAmount();
    }
}
