package com.soft_arex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactForwardingController {
    @RequestMapping(value = { "/", "/{path:[^\\.]*}" })
    public String forwardToIndex() {
        return "forward:/index.html";
    }
}