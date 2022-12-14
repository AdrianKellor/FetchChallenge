package com.challenge.restservice.output

import com.challenge.restservice.data.Receipt

class ReceiptPostOutput(receipt: Receipt) {
    val id = receipt.id
}

class ReceiptPointsOutput(receipt: Receipt) {
    val points = receipt.points
}