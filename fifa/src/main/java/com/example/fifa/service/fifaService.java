package com.example.fifa.service;

import com.example.fifa.model.User;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
public class fifaService {


    private final String apiKey = "test_ca3e45444908cc35bdbbd1cc718abf0c391a08154b98f3952c4fdbdb49d8038defe8d04e6d233bd35cf2fabdeb93fb0d";
    private final String baseUrl = "https://open.api.nexon.com/fconline/v1";

    private final RestTemplate restTemplate = new RestTemplate();
    private final Gson gson = new Gson();

    /**
     * 닉네임으로 OUID를 조회합니다.
     * @param nickName 유저의 닉네임
     * @return OUID 문자열
     */
    public String getOuidByNickName(String nickName) {
        String url = baseUrl + "/id";
        String response = restTemplate.getForObject(url + "?nickName=" + nickName, String.class);

        // JSON 응답에서 OUID 추출
        return gson.fromJson(response, User.class).getOuid();
    }

    /**
     * OUID로 유저의 기본 정보를 조회합니다.
     * @param ouid 유저의 OUID
     * @return UserBasic 객체
     */
    public UserBasic getUserBasicInfo(String ouid) {
        String url = baseUrl + "/user/basic?ouid=" + ouid;
        String response = restTemplate.getForObject(url, String.class);

        // JSON 응답을 UserBasic 객체로 변환
        return gson.fromJson(response, UserBasic.class);
    }

    /**
     * OUID로 유저의 역대 최고 등급을 조회합니다.
     * @param ouid 유저의 OUID
     * @return MaxDivision 객체
     */
    public MaxDivision getUserMaxDivision(String ouid) {
        String url = baseUrl + "/user/maxdivision?ouid=" + ouid;
        String response = restTemplate.getForObject(url, String.class);

        // JSON 응답을 MaxDivision 객체로 변환
        return gson.fromJson(response, MaxDivision.class);
    }

    /**
     * OUID로 유저의 매치 기록을 조회합니다.
     * @param ouid 유저의 OUID
     * @return MatchIdList 객체
     */
    public MatchIdList getUserMatchHistory(String ouid) {
        String url = baseUrl + "/user/match?ouid=" + ouid;
        String response = restTemplate.getForObject(url, String.class);

        // JSON 배열을 MatchIdList 객체로 변환
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> matchIdList = gson.fromJson(response, listType);

        return new MatchIdList(matchIdList);
    }

    /**
     * OUID로 유저의 거래 기록을 조회합니다.
     * @param ouid 유저의 OUID
     * @return TradeList 객체
     */
    public TradeList getUserTradeHistory(String ouid) {
        String url = baseUrl + "/user/trade?ouid=" + ouid;
        String response = restTemplate.getForObject(url, String.class);

        // JSON 배열을 TradeList 객체로 변환
        Type listType = new TypeToken<List<TradeList.Trade>>() {}.getType();
        List<TradeList.Trade> tradeList = gson.fromJson(response, listType);

        return new TradeList(tradeList);
    }
}