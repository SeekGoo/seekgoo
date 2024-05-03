package com.seekgu.seekguboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SeekguboardController {

    @RequestMapping("/seekgooboardwrite")
    public String seekgooboardwrite(Model model){
        return "seekgooboardwrite";
    }
}
