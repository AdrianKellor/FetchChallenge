package com.challenge.restservice.data

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

class Receipt(retailer: String, date: LocalDate, time: LocalTime, total: BigDecimal) {
    var id: String? = null
    var retailer: String = retailer
    var purchaseDate: LocalDate = date
    var purchaseTime: LocalTime = time
    var total = total
    var items: Array<Item> = emptyArray()
    var points: Int = 0
}

class Item(desc: String, price: BigDecimal) {
    var shortDescription: String = desc
    var price: BigDecimal = price
}