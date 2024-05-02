package com.seekgu.mapinfo.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.seekgu.mapinfo.domain.dto.Place;
import com.seekgu.utils.ApiUtil;
import com.seekgu.utils.kakao.KakaoUtil;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/map")
@RequiredArgsConstructor
public class MapInfoApi {

    private final KakaoUtil kakaoUtil;

    @GetMapping("/search")
    public List<Place> search(@RequestParam(name = "keyword") String keyword) {
        JSONArray jsonArray = (JSONArray) kakaoUtil.getMapList(keyword);
        List<Place> places = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            Place place = new Place();
            place.setPlaceName(jsonObject.get("place_name").toString());
            place.setX(Double.parseDouble(jsonObject.get("x").toString()));
            place.setY(Double.parseDouble(jsonObject.get("y").toString()));
            place.setRoadAddressName(jsonObject.get("road_address_name").toString());
            place.setDistance(Integer.parseInt((String) jsonObject.get("distance")));
            places.add(place);
        }
        return places;
    }
}
