package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final UserRepository userDao;
    private final SmokerInfoRepository smokeDao;

    public ProfileController(UserRepository userDao, SmokerInfoRepository smokeDao){
        this.smokeDao = smokeDao;
        this.userDao = userDao;
    }


}
//    public String SmokingCostSaved(Integer cocs, Integer dsf){
//        int total = cocs * dsf;
//        String out = String.valueOf(total);
//        return out;
//    }
//
//    private String convertSmokeInfoJson(SmokerInfo s, User u, String total) {
//        // Create output collection
//        Map<String, String> temp = new HashMap<>();
//
//        // Add values to temp object
//        temp.put("costOfCigsSaved", String.valueOf(s.getCost_of_cigs_saved()));
//        temp.put("totalDaysSmokeFree", String.valueOf(s.getTotal_days_smoke_free()));
//        temp.put("city", u.getCity());
//        temp.put("state", u.getState());
//        temp.put("points", String.valueOf(s.getPoints()));
//        temp.put("totalSaved", total);
//
//        // Create new Gson object and add contents and convert to Json
//        Gson g = new Gson();
//        String out = g.toJson(temp);
//
//        return out;
//    }
//
//    private String convertUserToJson(Boolean isUser){
//        if (isUser == false){
//            Map<String, String> temp = new HashMap<>();
//            temp.put("isUser", "false");
//            Gson g = new Gson();
//            String out = g.toJson(temp);
//            return out;
//        }else {
//            return "call this method with a username";
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String profileInfo(@RequestParam String username){
//        User u = userDao.findByUsername(username);
//        if (u != null){
//        SmokerInfo s = smokeDao.getOne(u.getId());
//        String total = SmokingCostSaved( s.getCost_of_cigs_saved(), s.getTotal_days_smoke_free());
//        String profileToJson = convertSmokeInfoJson(s,u, total);
//        return profileToJson;
//        }else {
//            String failedProfile = convertUserToJson(false);
//            return failedProfile;
//        }
//    }
