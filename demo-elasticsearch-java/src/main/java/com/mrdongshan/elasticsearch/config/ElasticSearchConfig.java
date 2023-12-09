package com.mrdongshan.elasticsearch.config;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class ElasticSearchConfig {

    @Value("${es.hosts}")
    private String hosts;
    @Value("${es.name:}")
    private String username;
    @Value("${es.password:}")
    private String password;


    /**
     * 解析配置的字符串，转为HttpHost对象数组, hosts example:   127.0.0.1:9200,127.0.0.1:9300
     */
    private HttpHost[] toHttpHost() {
        if (!StringUtils.hasText(hosts)) {
            throw new RuntimeException("invalid elasticsearch configuration");
        }

        String[] hostArray = hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[hostArray.length];
        HttpHost httpHost;
        for (int i = 0; i < hostArray.length; i++) {
            String[] strings = hostArray[i].split(":");
            httpHost = new HttpHost(strings[0], Integer.parseInt(strings[1]), "http");
            httpHosts[i] = httpHost;
        }

        return httpHosts;
    }

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        HttpHost[] httpHosts = toHttpHost();
        RestClientTransport transport;
        if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
            // 有密码
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
            RestClient restClient = RestClient.builder(httpHosts).setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)).build();
            transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        } else {
            // 没有密码
            RestClient restClient = RestClient.builder(httpHosts).build();
            transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        }
        return new ElasticsearchClient(transport);
    }

    @Bean
    public ElasticsearchAsyncClient elasticsearchAsyncClient() {
        HttpHost[] httpHosts = toHttpHost();
        RestClientTransport transport;
        if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
            // 有密码
            RestClient restClient = RestClient.builder(httpHosts).build();
            transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        } else {
            // 没有密码
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
            RestClient restClient = RestClient.builder(httpHosts).setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)).build();
            transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        }
        return new ElasticsearchAsyncClient(transport);
    }

}
