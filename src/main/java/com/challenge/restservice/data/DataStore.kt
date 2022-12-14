package com.challenge.restservice.data

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class DataStore private constructor() {

    companion object {
        var instance = DataStore()
    }

    var data = ConcurrentHashMap<String, Receipt>()

    fun find(s: String): Receipt? {
        return data!![s]
    }

    fun store(receipt: Receipt): Boolean {
        if (receipt.id == null) {
            receipt.id = UUID.randomUUID().toString()
        }
        data!![receipt.id!!] = receipt
        return false
    }

}