package com.mrdongshan.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfiguration {

    @Value("${es.hosts}")
    private String hosts;
//    private int port;
//    private int connTimeout;
//    private int socketTimeout;
//    private int connectionRequestTimeout;

    @Bean
    public RestHighLevelClient initRestClient() {
        int port = 9200;
        RestClientBuilder builder = RestClient.builder(new HttpHost(hosts, port));
//                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
//                        .setConnectTimeout(connTimeout)
//                        .setSocketTimeout(socketTimeout)
//                        .setConnectionRequestTimeout(connectionRequestTimeout));
        return new RestHighLevelClient(builder);
    }
}