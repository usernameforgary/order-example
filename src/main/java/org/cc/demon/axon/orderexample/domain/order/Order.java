package org.cc.demon.axon.orderexample.domain.order;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class Order {
   @AggregateIdentifier
   private String id;
   private String productId;
   private Integer mount;

   public Order() {}

   @CommandHandler
   public Order(CreateOrderCommand cmd) {
      AggregateLifecycle.apply(new OrderCreatedEvent(cmd.getId(), cmd.getProductId(), cmd.getAmount()));
   }

   @EventSourcingHandler
   public void on(OrderCreatedEvent evt) {
      this.id = evt.getId();
      this.productId = evt.getProductId();
      this.mount = evt.getAmount();
   }
}
