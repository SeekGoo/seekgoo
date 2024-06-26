package com.seekgu.utils.kakao;


import com.seekgu.utils.kakao.exception.KaKaoInvalidParsingException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KakaoUtil {

    @Value(value = "${kakao.appkey}")
    private String appkey;

    @Value(value = "${geo.lat}")
    private double latitude;

    @Value(value = "${geo.long}")
    private double longitude;

    public Object getMapList(String keyword){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("Authorization", "KakaoAK " + appkey);

        String url = "https://dapi.kakao.com/v2/local/search/keyword.json?"
                + "query=" + keyword
                + "&y=" + latitude
                + "&x=" + longitude
                + "&radius=1000"
                + "&size=5";

        HttpEntity entity = new HttpEntity<>(headers);

        ResponseEntity response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(response.getBody().toString());
            return jsonObject.get("documents");
        } catch (ParseException e) {
            throw new KaKaoInvalidParsingException("카카오 맵 조회 결과 파싱과정에서 예외가 발생하였습니다.");
        }
    }
}
