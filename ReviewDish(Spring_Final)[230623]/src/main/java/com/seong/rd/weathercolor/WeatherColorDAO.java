package com.seong.rd.weathercolor;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seong.rd.sns.SNSMsg;

@Service
public class WeatherColorDAO {
	HttpsURLConnection huc = null;

	@Autowired
	private SqlSession ss;

	public void reg(SNSMsg s) {
		try {
			
			
			URL url = new URL(
					"https://api.openweathermap.org/data/2.5/weather?appid="api키"&q=seoul&units=metric&lang=kr");
			huc = (HttpsURLConnection) url.openConnection();

			InputStream is = huc.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
//			BufferedReader br = new BufferedReader(isr);
//			System.out.println(br.readLine());


			// JSON 파싱
			JSONParser jp = new JSONParser();

			JSONObject jo = (JSONObject) jp.parse(isr);// 전체 다 불러오기(Object로 반환하기 때문에, 값을 입력할때 해당 자료형에 맞게 형변환)

			JSONArray ja = (JSONArray) jo.get("weather"); // 객체 타입 데이터를 배열 타입으로 형변환

			JSONObject w = (JSONObject) ja.get(0); // 배열의 0번째를 객체로

			// ★ 해당부분 숙지
			JSONObject m = (JSONObject) jo.get("main"); // main 오브젝트를 불러서 다른 객체로 받아옴

			Weather wc = new Weather(BigDecimal.valueOf(Double.parseDouble(m.get("temp").toString())),
					BigDecimal.valueOf(Double.parseDouble(m.get("humidity").toString())),
					w.get("description").toString(), s.getRs_color());

			System.out.println("zz");

			// csv에 쓰기
			System.out.println(w.get("description")); // 해당 객체의 description 속성을 출력
			System.out.println(m.get("temp"));
			System.out.println(m.get("humidity"));
			System.out.println(s.getRs_color());

			if (ss.getMapper(WeatherMapper.class).write(wc) == 1) {

			} else {

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
