package com.mrdongshan.elasticsearch.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final RestHighLevelClient restHighLevelClient;


    /**
     * 创建索引 request  put test_index
     */
    @GetMapping("createIndex")
    public void createIndex() throws IOException {
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("test_index");
        // 2、执行请求
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("createIndexResponse = " + createIndexResponse);
    }

    /**
     * 判断索引是否存在
     */
    @GetMapping("existIndex")
    public void existIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("test_index");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println("exists = " + exists);
    }

    /**
     * 测试删除索引
     */
    @GetMapping("deleteIndex")
    public void deleteIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("test_index");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println("delete = " + delete);
    }
}
