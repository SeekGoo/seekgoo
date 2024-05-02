package com.seekgu.utils.kakao;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KakaoUtilTest {

    @Autowired
    KakaoUtil kakaoUtil = new KakaoUtil();

    @Test
    void getMapList() {
        JSONArray result = (JSONArray) kakaoUtil.getMapList("호랑이");
        System.out.println(result);
    }
}