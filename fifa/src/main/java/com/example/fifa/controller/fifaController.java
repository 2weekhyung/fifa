package com.example.fifa.controller;

import com.example.fifa.model.User;
import com.example.fifa.model.UserBasic;
import com.example.fifa.service.fifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class fifaController {

    @Autowired
    private fifaService service; // 서비스 클래스 인스턴스 주입

    /**
     * FIFA 정보 조회를 위한 메서드
     * @param nickName 유저의 닉네임
     * @return ModelAndView 객체를 반환하여 뷰와 데이터를 함께 반환합니다.
     */
    @GetMapping("/fifaInfo")
    public UserBasic fifaInfo(@RequestParam("nickName") String nickName) {
        String ouid = service.getOuidByNickName(nickName);

        if (ouid == null) {
            return null; // 유저를 찾을 수 없을 경우 적절한 처리를 합니다.
        }

        return service.getUserBasicInfo(ouid);
    }

    @GetMapping("/fifaMaxDivision")
    public MaxDivision getUserMaxDivision(@RequestParam("ouid") String ouid) {
        return service.getUserMaxDivision(ouid);
    }

    @GetMapping("/fifaMatchHistory")
    public MatchIdList getUserMatchHistory(@RequestParam("ouid") String ouid) {
        return service.getUserMatchHistory(ouid);
    }

    @GetMapping("/fifaTradeHistory")
    public TradeList getUserTradeHistory(@RequestParam("ouid") String ouid) {
        return service.getUserTradeHistory(ouid);
    }
}