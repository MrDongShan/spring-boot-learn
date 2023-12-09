package com.mrdongshan.elasticsearch.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "book")
public class Book {
    @Id
    private String name;
    private Integer size;
}
