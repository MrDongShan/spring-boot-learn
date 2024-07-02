package com.mrdongshan.elk.dao;

import com.mrdongshan.elk.pojo.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {
  
}