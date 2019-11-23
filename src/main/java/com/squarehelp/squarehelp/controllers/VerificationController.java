package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.Verification;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import com.squarehelp.squarehelp.repositories.VerificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.squarehelp.squarehelp.util.Calculator.calcMoneySaved;

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

    @GetMapping("/verification/{user_id}/")
    public String getVerificationsView(Model model, @PathVariable long user_id) {
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());

        // Get all verification requests
        List<Verification> temp = veriDao.findAllByOriginator_user_id(user_id);

        for(Verification t : temp) {
            System.out.println(t.getApprover_name());
        }

        model.addAttribute("verifications", temp);
        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("moneySaved", moneySaved);

        return "verification";
    }

    @GetMapping("/verification/{user_id}/form")
    public String getVerificationForm(Model model, @PathVariable long user_id) {
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());

        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("moneySaved", moneySaved);
        return "verification-form";
    }

}
