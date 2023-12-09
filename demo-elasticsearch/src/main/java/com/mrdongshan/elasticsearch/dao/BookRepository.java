package com.mrdongshan.elasticsearch.dao;

import com.mrdongshan.elasticsearch.pojo.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {

}
