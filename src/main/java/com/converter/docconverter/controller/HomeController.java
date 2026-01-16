package com.converter.docconverter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/privacy")
    public String privacy() { return "privacy"; }

    @GetMapping("/about")
    public String about() { return "about"; }

    @GetMapping("/contact")
    public String contact() { return "contact"; }

    @GetMapping("/features")
    public String features() {
        return "features";
    }
    @GetMapping("/terms")
    public String terms() {
        return "terms";
    }
    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

}
