package org.cc.demon.axon.orderexample.domain.product

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class AddProductCommand(
        @TargetAggregateIdentifier
        val id:String,
        val amount: Int,
        val price: Double,
        val name: String,
        val description: String
);

data class ProductAddedEvent (
        val id:String,
        val amount: Int,
        val price: Double,
        val name: String,
        val description: String
)

data class DecreaseAmountCommand (
        @TargetAggregateIdentifier
        val productId:String,
        val amount: Int
)

data class AmountDecreasedEvent(
        val productId:String,
        val amount: Int
)