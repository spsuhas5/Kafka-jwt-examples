package com.dailycodebuffer.kafka.kafkademo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String bookName;
    private String isbn;
}
