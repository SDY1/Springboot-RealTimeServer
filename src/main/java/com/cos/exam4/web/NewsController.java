package com.cos.exam4.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.exam4.domain.News;
import com.cos.exam4.domain.NewsRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class NewsController {

//	private final NewsReactRepository newsRepository;
	private final NewsRepository newsRepository;

//	@CrossOrigin // 서버는 다른 도메인의 자바스크립트 요청을 거부함(이를 허용해주는 어노테이션)
//	// SSE프로토콜 설정
//	@GetMapping(value = "/news", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	public Flux<News>findAll(){
//		return newsRepository.mFindAll()
//				.subscribeOn(Schedulers.boundedElastic()); // 유지되는 응답선마다 쓰레드 만들어짐(비동기서버 임에도 불구하고)
//	}
//	
//	@PostMapping("/news")
//	public News save(@RequestBody News News){
//		return newsRepository.save(News);
//	}
	
	@GetMapping("/news")
	   public List<News> findAll(){
	      return newsRepository.findAll();
	   }
}