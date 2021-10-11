package com.cos.exam4.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class NaverCrawTest {

	@Test
	public void test1() {
		RestTemplate rt = new RestTemplate();
		String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=103&oid=437&aid=0000277493";
		String html = rt.getForObject(url, String.class);

		Document doc = Jsoup.parse(html); 

		Element companyElement = doc.selectFirst(".press_logo img");
		Element titleElement = doc.selectFirst("#articleTitle");
		Element createdAtElement = doc.selectFirst(".t11");
		
		String company = companyElement.attr("alt");
		String title = titleElement.text();
		String createdAt = createdAtElement.text();
		
		createdAt = createdAt.substring(0, 10).replace(".", "-");
		String current = String.valueOf(LocalDateTime.now().minusDays(1)).substring(0, 10);
//		createdAt = "2021-10-09";
		if(createdAt.compareTo(current) < 0) {
			System.out.println("createdAt가 전 날 createdAt: " + createdAt + "/current: " + current);
		}else {
			System.out.println("createdAt가 다음 날이나 같은날 createdAt: " + createdAt + "/current: " + current);
		}
			
		Timestamp ts = Timestamp.valueOf(createdAt + " 00:00:00");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(ts);
		cal.add(Calendar.HOUR, 9);
		ts.setTime(cal.getTime().getTime());
		System.out.println(ts);
		
//		LocalDateTime t = LocalDateTime.now().minusDays(1);
	}
}
