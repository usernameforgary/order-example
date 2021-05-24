package org.cc.demon.axon.orderexample.api.vo

import java.util.*

data class AddProductVo (
        val amount: Int,
        val price: Double,
        val name: String,
        val description: String
)