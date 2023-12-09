package com.mrdongshan.elasticsearch.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.DeleteIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import com.mrdongshan.elasticsearch.pojo.Hotel;
import com.mrdongshan.elasticsearch.pojo.HotelDoc;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.mrdongshan.elasticsearch.config.HotelConstants.USER_MAPPING;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final ElasticsearchClient elasticsearchClient;

    @GetMapping("createIndex")
    public void createIndex() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(USER_MAPPING.getBytes(StandardCharsets.UTF_8));

        // 实例化建造器
        CreateIndexRequest.Builder builder = new CreateIndexRequest.Builder();
        // 设置索引库的名字
        builder.index("user");
        // 使用 json 方式创建索引库
        builder.withJson(inputStream);
        // 构造请求
        CreateIndexRequest request = builder.build();

        // 使用 esClient 测试请求并返回结果
        System.out.println(elasticsearchClient.indices().create(request).acknowledged());
    }

    @GetMapping("deleteIndex")
    public void deleteIndex() throws IOException {
        DeleteIndexRequest.Builder builder = new DeleteIndexRequest.Builder();
        builder.index("user");

        System.out.println(elasticsearchClient.indices().delete(builder.build()).acknowledged());
    }

    @GetMapping("existsIndex")
    public void existsIndex() throws IOException {
        ExistsRequest.Builder builder = new ExistsRequest.Builder();
        builder.index("user");

        System.out.println(elasticsearchClient.indices().exists(builder.build()).value());
    }

    @GetMapping("addDocument")
    public void addDocument() throws IOException {
        Hotel hotel = Hotel.buildDefaultData();
        // 转换为文档类型
        HotelDoc doc = new HotelDoc(hotel);

        // 获取建造器
        IndexRequest.Builder<HotelDoc> builder = new IndexRequest.Builder<>();
        // 指定索引库名
        builder.index("hotel");
        // 设置文档 id
        builder.id(doc.getId().toString());
        // 读入文档
        builder.document(doc);

        // 执行插入操作，获取 result 并打印
        System.out.println(elasticsearchClient.index(builder.build()).result());
    }

    @GetMapping("getDocumentById")
    public void getDocumentById() throws IOException {
        GetRequest.Builder builder = new GetRequest.Builder();
        // 设置索引库名
        builder.index("hotel");
        // 设置文档 id
        builder.id("1");

        // 传入 GetRequest	设置返回的对象类型
        System.out.println(elasticsearchClient.get(builder.build(), HotelDoc.class).source());
    }

    @GetMapping("updateDocumentById")
    public void updateDocumentById() throws IOException {
        Hotel hotel = Hotel.buildDefaultData();
        // 转换为文档类型
        HotelDoc doc = new HotelDoc(hotel);
        // 修改数据
        doc.setName("修改酒店名字测试");

        //  修改的类型  数据类型？
        UpdateRequest.Builder<HotelDoc, HotelDoc> builder = new UpdateRequest.Builder<>();
        // 设置索引库名
        builder.index("hotel");
        // 设置 id
        builder.id("1");
        // 读入文档对象
        builder.doc(doc);

        // 执行修改操作 修改类型 获取状态
        System.out.println(elasticsearchClient.update(builder.build(), HotelDoc.class).result());
    }

    @GetMapping("deleteDocumentById")
    public void deleteDocumentById() throws IOException {
        DeleteRequest.Builder builder = new DeleteRequest.Builder();
        builder.index("hotel");
        builder.id("1");

        System.out.println(elasticsearchClient.delete(builder.build()).result());
    }

    @GetMapping("bulkDocument")
    void bulkDocument() throws IOException {
        // 使用 mybatis-plus 获取所有的记录
        List<Hotel> list = Hotel.buildDefaultDataList();

        BulkRequest.Builder builder = new BulkRequest.Builder();
        // 设置索引库
        builder.index("hotel");

        // 遍历记录
        for (Hotel hotel : list) {
            // 添加数据
            builder.operations(o -> o //lambda
                    .create(v -> v //lambda
                            // 读入 id
                            .id(hotel.getId().toString())
                            // 读入文档
                            .document(new HotelDoc(hotel))
                            // 设置索引库
                            .index("hotel")
                    )
            );
        }

        // 执行批量插入，并获取是否发生了错误
        System.out.println(elasticsearchClient.bulk(builder.build()).errors());
    }


}
