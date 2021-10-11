package com.cos.exam4.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewsRepository extends MongoRepository<News, String>{

}
