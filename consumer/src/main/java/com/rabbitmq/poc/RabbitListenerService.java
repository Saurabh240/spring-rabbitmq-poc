package com.rabbitmq.poc;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitListenerService {
    private final OrderProcessor processor;

    public RabbitListenerService(OrderProcessor processor) {
        this.processor = processor;
    }

    @RabbitListener(queues = "order.queue")
    public void consume(Order order) {
        processor.save(order);
    }
}
