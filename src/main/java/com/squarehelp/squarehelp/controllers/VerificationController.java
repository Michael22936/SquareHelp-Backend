package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.models.Verification;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import com.squarehelp.squarehelp.repositories.VerificationRepository;
import com.squarehelp.squarehelp.services.NotificationServices;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.squarehelp.squarehelp.util.Calculator.*;
import static com.squarehelp.squarehelp.util.UnreadNotifications.unreadNotificationsCount;

@Controller
public class VerificationController {
    private final SmokerInfoRepository smokeDao;
    private final UserRepository userDao;
    private final VerificationRepository veriDao;
    private final NotificationRepository notiDao;
    private final NotificationServices notiServices;

    public VerificationController(VerificationRepository veriDao, SmokerInfoRepository smokeDao, UserRepository userDao, NotificationRepository notiDao, NotificationServices notiServices) {
        this.veriDao = veriDao;
        this.smokeDao = smokeDao;
        this.userDao = userDao;
        this.notiDao = notiDao;
        this.notiServices = notiServices;
    }

    @GetMapping("/verification")
    public String passingDashboard(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        SmokerInfo smokerInfo = smokeDao.getOne(id);
        User signedInUser = userDao.getOne(id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());

        // Get all verification requests
        List<Verification> sentVeri = veriDao.findAllByOriginator_user_id(id);
        List<Verification> recVeri = veriDao.findAllByApprover_name(user.getUsername());


        // Verify day quit smoking
        Integer veriId =  (int) veriDao.count();
        if (veriId == 0){
            // no action
        }else{
        Verification veriApprove = veriDao.findById(veriId);
        String veriDayCreate = veriApprove.getDay_created();

        // Saves user as verified
        veriApproval(veriApprove.getOriginator_user_id(),veriApprove, veriApprove.getIs_changes_updated() ,veriApprove.getIs_approved() ,veriApprove.getIs_pending(), userDao, id, veriDayCreate, veriApprove.getSender_name());

        }

//        int userPointsTotal = userPointsCalculator(days, signedInUser.getSmokerInfo().getPoints());

        //========= Gets the count of unread notifications
        int unreadNotifications = unreadNotificationsCount(notiDao, id);

        model.addAttribute("sentVeri", sentVeri);
        model.addAttribute("recVeri", recVeri);
        model.addAttribute("alertCount", unreadNotifications); // shows count for unread notifications
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("moneySaved", moneySaved);

        return "verification";
    }

    // Used to view individual verification request (in order to approve or deny)
    @GetMapping("/verification/{veriId}/view")
    public String viewOneVerification(Model model, @PathVariable int veriId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        Verification v = veriDao.findById(veriId);
        User from = userDao.findUserById((long) v.getOriginator_user_id());

        model.addAttribute("veri", v);
        model.addAttribute("from", from);

        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("smoke", smokeDao.getOne(id));

        return "verification-view";
    }

    // Used to save individual verification request (if isApproved is present)
    @PostMapping("/verification/{veriId}/view")
    public String saveOneVerification(Model model, @PathVariable int veriId, @RequestParam String isApproved) {
        Verification v = veriDao.findById(veriId);

        if (isApproved.equalsIgnoreCase("on")) {
            v.setIs_approved(true);
            v.setIs_pending(false);
            veriDao.save(v);
            return "redirect:/verification/";
        } else {
            v.setIs_approved(false);
            v.setIs_pending(false);
            veriDao.save(v);
            return "redirect:/verification/";

        }
    }

    // The page to host the 'search' route
    @GetMapping("/verification/{user_id}/form")
    public String getVerificationForm(Model model, @PathVariable long user_id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());

        //========= Gets the count of unread notifications
        int unreadNotifications = unreadNotificationsCount(notiDao, id);

        model.addAttribute("alertCount", unreadNotifications); // shows count for unread notifications
        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("moneySaved", moneySaved);

        return "verification-form";
    }

    // Actual page to create the form with recipient selected
    @GetMapping("/verification/{user_id}/form/send/{recip}")
    public String getVerificationFormSend(Model model, @PathVariable long user_id, @PathVariable long recip) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());
        int unreadNotifications = unreadNotificationsCount(notiDao, id);

        User ru = userDao.findUserById(recip);

        model.addAttribute("alertCount", unreadNotifications); // shows count for unread notifications
        model.addAttribute("recipient", ru);
        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("moneySaved", moneySaved);
        return "verification-form-create";
    }

    // Generate the form and send
    @PostMapping("/verification/{user_id}/form/send/{recip}")
    public String postVerificationFormSend(Model model, @PathVariable long user_id, @PathVariable long recip, @RequestParam String date) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User senderUser = userDao.getOne(user.getId());
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());

        // Find recipients username (ru)
        User ru = userDao.findUserById(recip);

        // Find Sender username (sender)
        String senderUsername = senderUser.getUsername();

        // Convert user id to int for constructor
        int uid = Integer.parseInt(String.valueOf(user_id));

        // Create verification and notification
        Verification v = new Verification(uid, ru.getUsername(), date, 1, false, user, true, false,senderUsername );
        veriDao.save(v);
        notiServices.createNotification(user.getUsername(), recip, "veri");

        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("moneySaved", moneySaved);
        return "redirect:/verification/";
    }

    // For finding usernames of recipients of verification form
    @RequestMapping("/search")
    @ResponseBody
    public List<User> sendMatchingUser(@RequestParam String username) {
        System.out.println(userDao.findByUsernameContaining(username));
        return userDao.findByUsernameContaining(username);
    }
}
