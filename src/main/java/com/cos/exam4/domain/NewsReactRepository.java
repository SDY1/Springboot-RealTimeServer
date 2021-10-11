package com.cos.exam4.domain;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;

import reactor.core.publisher.Flux;

public interface NewsReactRepository extends ReactiveMongoRepository<News, String>{
	@Tailable // 커서를 계속 열어놓는 어노테이션(데이터가 다들어와도 기다림)
	@Query("{}")
	Flux<News>mFindAll();
}
