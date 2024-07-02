package com.mrdongshan.elk.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

//@Id 用于标识哪一个字段是ID, 之后使用方法findById时会根据此标识字段查找
//@Document(indexName = "book") 标识所属的索引

@Data
@Document(indexName = "book")
public class Book {
    @Id
    private String name;
    private Integer size;
}