package com.challenge.restservice.points

import com.challenge.restservice.data.Item
import com.challenge.restservice.data.Receipt
import java.math.BigDecimal
import java.math.RoundingMode

interface ItemCalculators {
    fun calculatePoints(itme: Item): PointResult?
}

class ItemDescriptionPoints: ItemCalculators {
    override fun calculatePoints(item: Item): PointResult? {
        return if (item.shortDescription.trim().length % 3 == 0) {
            PointResult( item.price.multiply( BigDecimal("0.2") ).setScale(0, RoundingMode.HALF_UP).toInt() , "Item Description length is multiple of 3"  )
        } else {
            null
        }
    }

}