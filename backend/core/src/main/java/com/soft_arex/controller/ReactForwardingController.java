package com.soft_arex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactForwardingController {
    @RequestMapping(value = {
            "/",
            "/login",
            "/signup",
            "/edit-profile",
            "/change-password",
            "/verify-email",
            "/questionnaires/**",
            "/answers/**",
            "/field"
    })
    public String forwardToIndex() {
        return "forward:/index.html";
    }
}