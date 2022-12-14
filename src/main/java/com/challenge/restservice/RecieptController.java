package com.challenge.restservice;

import com.challenge.restservice.data.Receipt;
import com.challenge.restservice.input.ReceiptInput;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RecieptController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/receipts/{id}/points")
    public Receipt greeting(@PathVariable Long id) {
        return new Receipt(counter.incrementAndGet(), String.format(template, id.toString()));
    }

    @PostMapping("/receipts/process")
    Receipt newEmployee(@RequestBody ReceiptInput input) {
        return new Receipt(counter.incrementAndGet(), String.format(template, input.getItems().length));
    }
}
