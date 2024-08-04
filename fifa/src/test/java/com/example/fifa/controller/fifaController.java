package com.example.fifa.controller;

import com.example.fifa.service.fifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class fifaController {

    @Autowired
    fifaService service;

    @RequestMapping(value = "fifaInfo")
    public ModelAndView fifaInfo(String nickName) {
        ModelAndView mav = new ModelAndView("/fifaView");
        service.fifaId(nickName);

        return mav;
    }
}
