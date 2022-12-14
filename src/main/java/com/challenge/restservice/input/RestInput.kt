package com.challenge.restservice.input

import com.challenge.restservice.data.Item
import com.challenge.restservice.data.Receipt
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

class InputValidationError(msg: String): Exception(msg) {

}

class ReceiptInput {
    var retailer: String? = null
    var purchaseDate: LocalDate? = null
    var purchaseTime: LocalTime? = null
    var total: String? = null
    var totalBD: BigDecimal? = null
    var items: Array<ReceiptItemInput> = arrayOf(ReceiptItemInput())

    fun validateAndGetReceipt(): Receipt {
        when {
            retailer == null -> throw InputValidationError("No retailer name")
            purchaseDate == null -> throw InputValidationError("No purchase date")
            purchaseTime == null -> throw InputValidationError("No purchase time")
            total == null -> throw InputValidationError("No total")
        }

        try {
            totalBD = BigDecimal(total!!)
        } catch(e: Exception) {
            throw InputValidationError("Invalid total of " + total!!)
        }

        val result = Receipt(retailer!!, purchaseDate!!, purchaseTime!!, totalBD!!)

        result.items = items.map { inputItem ->
            when {
                inputItem.shortDescription == null -> throw InputValidationError("Item has no description")
                inputItem.price == null -> throw InputValidationError("Item has no prices")
            }
            Item(inputItem.shortDescription!!, inputItem.price!!)
        }.toTypedArray()

        return result
    }
}


class ReceiptItemInput {
    var shortDescription: String? = null
    var price: BigDecimal? = null
}