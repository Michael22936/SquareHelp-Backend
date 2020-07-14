package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class CommunityController {
    @GetMapping("/com")
    @ResponseBody
    public String CommunityPage(){

        return "hello world";
    }

}
