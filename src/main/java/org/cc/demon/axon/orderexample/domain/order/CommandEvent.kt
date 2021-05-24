package org.cc.demon.axon.orderexample.domain.order

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class CreateOrderCommand (
        @TargetAggregateIdentifier
        var id: String,
        var productId: String,
        var amount: Int
);

data class OrderCreatedEvent (
        var id: String,
        var productId: String,
        var amount: Int
);