package com.mpinfo.seucarro.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public  String get() {
        return "API dos carros!";
    }
/*
    @GetMapping("/userInfo")
    public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) {
        return user;
    } */
}



