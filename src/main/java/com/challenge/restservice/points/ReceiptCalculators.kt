package com.challenge.restservice.points

import com.challenge.restservice.data.Receipt
import java.math.BigDecimal
import java.time.LocalTime

interface ReceiptPointCalculator {
   fun calculatePoints(receipt: Receipt): PointResult?
}

class PointResult(amount: Int, desc: String? = null) {
    var description: String? = desc
    var amount: Int = amount
}

class OnePointPerAlphaNum: ReceiptPointCalculator {
    override fun calculatePoints(receipt: Receipt): PointResult? {
        return receipt.retailer?.let {
             PointResult(it.filter { it.isLetterOrDigit() }.length, "One point for every alphanumeric character in retailer name.")
        } ?: run {
            null
        }
    }

}

class RoundDollarAmount: ReceiptPointCalculator {
    override fun calculatePoints(receipt: Receipt): PointResult? {

        return if (receipt.total.remainder( BigDecimal.ONE ).compareTo(BigDecimal.ZERO) == 0 ) {
            PointResult(50, "Round dollar amount with no cents")
        } else {
            null
        }
    }
}

class QuarterAmount: ReceiptPointCalculator {
    override fun calculatePoints(receipt: Receipt): PointResult? {
        return if (receipt.total.remainder(BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) {
            PointResult(25, "Total is a multiple of 0.25")
        } else {
            null
        }
    }
}

class ItemPairs: ReceiptPointCalculator {
    override fun calculatePoints(receipt: Receipt): PointResult? {
        return PointResult((receipt.items.size / 2) * 5, "5 points for every two items on the receipt")
    }
}

class OddPurchaseDay: ReceiptPointCalculator {
    override fun calculatePoints(receipt: Receipt): PointResult? {
        return if (receipt.purchaseDate.dayOfMonth % 2 == 1) {
            PointResult(6, "Day in the purchase date is odd")
        } else {
            null
        }
    }
}

class TwoToFourPM: ReceiptPointCalculator {
    override fun calculatePoints(receipt: Receipt): PointResult? {
        return if (receipt.purchaseTime.isAfter(LocalTime.of(14,0))
                   && receipt.purchaseTime.isBefore(LocalTime.of(16,0))) {
            PointResult(10, "Purchase is after 2:00pm and before 4:00pm")
        } else {
            null
        }
    }
}
