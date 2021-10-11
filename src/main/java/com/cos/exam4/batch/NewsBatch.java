package com.cos.exam4.batch;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.exam4.domain.News;
import com.cos.exam4.domain.NewsRepository;
import com.cos.exam4.util.NaverCraw;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NewsBatch {
	private final NewsRepository newsRepository;
	private final NaverCraw naverCraw;
	
	@Scheduled(cron="0 0 1 * * ?")
//	@Scheduled(fixedDelay = 1000 * 60 * 5)
	public void newsCrawAndSave() {
		List<News>newsList = naverCraw.collect();
		newsRepository.saveAll(newsList);
//		System.out.println("확인2");

	}
	
}