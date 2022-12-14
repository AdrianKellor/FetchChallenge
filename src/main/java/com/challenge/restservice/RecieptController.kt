package com.challenge.restservice

import com.challenge.restservice.data.DataStore
import com.challenge.restservice.data.Receipt
import com.challenge.restservice.input.InputValidationError
import com.challenge.restservice.input.ReceiptInput
import com.challenge.restservice.output.ReceiptPointsOutput
import com.challenge.restservice.output.ReceiptPostOutput
import com.challenge.restservice.points.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception
import java.util.concurrent.atomic.AtomicLong

@RestController
class RecieptController {


    private val receiptCalculators = arrayOf(
            OnePointPerAlphaNum(),
            RoundDollarAmount(),
            QuarterAmount(),
            ItemPairs(),
            OddPurchaseDay(),
            TwoToFourPM()
    )

    private val itemCalculators = arrayOf(ItemDescriptionPoints())

    @GetMapping("/receipts/{id}/points")
    fun getReceiptPoints(@PathVariable id: String): ReceiptPointsOutput {
        DataStore.instance.find(id)?.let { return ReceiptPointsOutput(it) }
        throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @PostMapping("/receipts/process")
    fun postReceipt(@RequestBody input: ReceiptInput): ReceiptPostOutput {
        try {
            val receipt = input.validateAndGetReceipt()
            val receiptPoints = receiptCalculators.map { calc -> calc.calculatePoints(receipt) }.sumOf { points -> points?.amount ?: 0 }
            val itemPoints = receipt.items.map { item ->
                itemCalculators.map { calc -> calc.calculatePoints(item) }.sumOf { points -> points?.amount ?: 0 }
            }.sumOf { it }
            receipt.points = receiptPoints + itemPoints
            DataStore.instance.store(receipt)
            return ReceiptPostOutput(receipt);
        } catch(e: InputValidationError) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, e.message)
        } catch(e: Exception) {
            e.printStackTrace(System.err)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
    }


}