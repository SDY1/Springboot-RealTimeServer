package com.cos.exam4.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.exam4.domain.News;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Component
public class NaverCraw {
	int aidNum = 277950;   //277493

	public List<News> collect() {
		RestTemplate rt = new RestTemplate();
		List<News> newsList = new ArrayList<>();
		String current = String.valueOf(LocalDateTime.now().minusDays(1)).substring(0, 10);

		for (;;) {
			try {
				String aid = String.format("%010d", aidNum);
				String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=103&oid=437&aid=" + aid;
				String html = rt.getForObject(url, String.class);

				Document doc = Jsoup.parse(html);

				Element createdAtElement = doc.selectFirst(".t11");
				String createdAt = createdAtElement.text();
				
				// 날짜 비교를 위해 데이터 정제
				createdAt = createdAt.substring(0, 10).replace(".", "-");
				
				// 현재 날짜와 비교해서 전 날짜만 가져옴
				if (createdAt.compareTo(current) < 0) {
					aidNum++;
					System.out.println("확인1" + aid);
					continue;
				} else if (createdAt.compareTo(current) == 0) {
					Element companyElement = doc.selectFirst(".press_logo img");
					Element titleElement = doc.selectFirst("#articleTitle");

					String company = companyElement.attr("alt");
					String title = titleElement.text() + aid;

					Timestamp ts = Timestamp.valueOf(createdAt + " 00:00:00"); // 형변환
					// 9시간 더하기
					Calendar cal = Calendar.getInstance();
					cal.setTime(ts);
					cal.add(Calendar.HOUR, 9);
					ts.setTime(cal.getTime().getTime());

					News news = News.builder().company(company).title(title).createdAt(ts).build();
					newsList.add(news); // try-catch문(널값처리)
					aidNum++;
				} else {
					break;
				}
			} catch (Exception e) {
				System.out.println("널 값");
				aidNum++;
			}
		}
		System.out.println(newsList);
		return newsList;
	}
}