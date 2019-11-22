package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VerificationController {
    private final SmokerInfoRepository smokeDao;
    private final UserRepository userDao;

    public VerificationController(SmokerInfoRepository smokeDao, UserRepository userDao) {
        this.smokeDao = smokeDao;
        this.userDao = userDao;
    }

    @GetMapping("/verification/{user_id}")
    public String getVerifyMessageView(Model model, @PathVariable long user_id){
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);


        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokerInfo);

        return "verification";
    }


}
