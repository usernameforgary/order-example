package org.cc.demon.axon.orderexample.api.vo

import java.util.*

data class CreateOrderVo(
    var productId: String,
    var amount: Int
)