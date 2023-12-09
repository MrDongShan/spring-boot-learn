package com.mrdongshan.elasticsearch.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Document(indexName = "user")
public class User implements Serializable {
    @Id
    private String id; // id
    private String username; // 用户名
    private String password; // 密码
}