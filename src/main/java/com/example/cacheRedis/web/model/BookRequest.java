package com.example.cacheRedis.web.model;

import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String author;
    private String category;
}
