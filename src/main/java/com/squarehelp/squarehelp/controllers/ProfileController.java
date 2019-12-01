package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.squarehelp.squarehelp.util.Calculator.calcMoneySaved;
import static com.squarehelp.squarehelp.util.UnreadNotifications.unreadNotificationsCount;

@Controller
public class ProfileController {

    private final UserRepository userDao;
    private final SmokerInfoRepository smokeDao;
    private final NotificationRepository notiDao;

    public ProfileController(UserRepository userDao, SmokerInfoRepository smokeDao, NotificationRepository notiDao) {
        this.smokeDao = smokeDao;
        this.userDao = userDao;
        this.notiDao = notiDao;
    }

    @GetMapping("/profile/{id}")
    public String showProfile(Model model, @PathVariable long id) {
        SmokerInfo smokerInfo = smokeDao.getOne(id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());

        //========= Gets the count of unread notifications
        int unreadNotifications = unreadNotificationsCount(notiDao, id);

        model.addAttribute("alertCount", unreadNotifications); // shows count for unread notifications
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("moneySaved",moneySaved);

        return "profile";
    }

    @GetMapping("/profile/{id}/edit")
    public String edit(Model model, @PathVariable long id, Model viewModel) {

        //========= Gets the count of unread notifications
        int unreadNotifications = unreadNotificationsCount(notiDao, id);

        model.addAttribute("alertCount", unreadNotifications); // shows count for unread notifications
        viewModel.addAttribute("users", userDao.getOne(id));
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("smoke", smokeDao.getOne(id));

        return "editprofile";
    }

    @PostMapping("/profile/{id}/edit")
    public String updateProfile(@PathVariable long id, @RequestParam String username, @RequestParam String city, @RequestParam String state, @RequestParam String profilePic) {
        User oldUserProfile = userDao.getOne(id);

        oldUserProfile.setUsername(username);
        oldUserProfile.setCity(city);
        oldUserProfile.setState(state);
        oldUserProfile.setProfilePic(profilePic);

        userDao.save(oldUserProfile);

        return "redirect:/profile/" + id;
    }

}
