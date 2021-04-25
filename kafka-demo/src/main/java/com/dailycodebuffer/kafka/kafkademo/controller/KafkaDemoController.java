package com.dailycodebuffer.kafka.kafkademo.controller;

import com.dailycodebuffer.kafka.kafkademo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class KafkaDemoController {

    @Autowired
    private KafkaTemplate/*<String, Book>*/ kafkaTemplate;

    private static final String TOPIC = "NewTopic";

    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable("message") String msg) {
        kafkaTemplate.send(TOPIC, msg);
        return "published successfully";
    }

    @PostMapping("/publish")
    public String publishMsg(@RequestBody Book book) {

        kafkaTemplate.send(TOPIC, book);
        return "published new message into topic successfully";
    }
}
