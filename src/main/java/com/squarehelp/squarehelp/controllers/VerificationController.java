package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.Verification;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import com.squarehelp.squarehelp.repositories.VerificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VerificationController {
    private final SmokerInfoRepository smokeDao;
    private final UserRepository userDao;
    private final VerificationRepository veriDao;

    public VerificationController(VerificationRepository veriDao, SmokerInfoRepository smokeDao, UserRepository userDao) {
        this.veriDao = veriDao;
        this.smokeDao = smokeDao;
        this.userDao = userDao;
    }

    @GetMapping("/verification/{user_id}")
    public String getVerificationsView(Model model, @PathVariable long user_id) {
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);



        model.addAttribute("verifications", veriDao.getOne(user_id));
        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokerInfo);

        return "verification";
    }

    @GetMapping("/verification/form/{user_id}")
    public String getVerifyFormView(Model model, @PathVariable long user_id){
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);



        model.addAttribute("verifications", veriDao.getOne(user_id));
        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokerInfo);

        return "verificationform";
    }



    @PostMapping("verification/{user_id}")
    public String postToVerifyList( @RequestParam(name = "date_request") String drequest,
                                             @RequestParam(name = "username") String user, Model model){

        int date = Integer.parseInt(drequest);

        Verification veri = new Verification();
        model.addAttribute("drequest", "Test");


        return "verification";
    }

}
