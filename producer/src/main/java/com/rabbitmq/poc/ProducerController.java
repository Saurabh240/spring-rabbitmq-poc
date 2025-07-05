package com.rabbitmq.poc;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class ProducerController {
    private final MessagePublisher publisher;

    public ProducerController(MessagePublisher publisher) {
        this.publisher = publisher;
    }

    @PostMapping
    public ResponseEntity<String> sendOrder(@RequestBody com.rabbitmq.poc.OrderRequest order) {
        publisher.publish(order);
        return ResponseEntity.ok("Order queued");
    }
}
