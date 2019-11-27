package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.models.Verification;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import com.squarehelp.squarehelp.repositories.VerificationRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

import static com.squarehelp.squarehelp.util.Calculator.avgPointsCalculator;
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

    @GetMapping("/verification")
    public String passingDashboard(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        SmokerInfo smokerInfo = smokeDao.getOne(id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());

        // Get all verification requests
        List<Verification> sentVeri = veriDao.findAllByOriginator_user_id(id);
        List<Verification> recVeri = veriDao.findAllByApprover_name(user.getUsername());

        model.addAttribute("sentVeri", sentVeri);
        model.addAttribute("recVeri", recVeri);

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

    // Used to save individual verification request
    @PostMapping("/verification/{veriId}/view")
    public String saveOneVerification(Model model, @PathVariable int veriId, @RequestParam String isApproved) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        Verification v = veriDao.findById(veriId);

        // Take in string from the form and convert on to boolean of true and save the request.
        // Do nothing otherwise. (remain false)
        boolean isApprovedBool = false;
        if (isApproved.equalsIgnoreCase("on")) isApprovedBool = true;

        if (isApprovedBool) {
            v.setIs_approved(isApprovedBool);
            veriDao.save(v);
        }

        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("smoke", smokeDao.getOne(id));

        return "redirect:/verification/";
    }

    // The page to host the 'search' route
    @GetMapping("/verification/{user_id}/form")
    public String getVerificationForm(Model model, @PathVariable long user_id) {
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());

        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("moneySaved", moneySaved);

        return "verification-form";
    }

    // For finding usernames of recipients of verification form
    @RequestMapping("/search")
    @ResponseBody
    public List<User> sendMatchingUser(@RequestParam String username) {
        System.out.println(userDao.findByUsernameContaining(username));
        return userDao.findByUsernameContaining(username);
    }

    // Actual page to create the form with recipient selected
    @GetMapping("/verification/{user_id}/form/send/{recip}")
    public String getVerificationFormSend(Model model, @PathVariable long user_id, @PathVariable long recip) {
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());

        User ru = userDao.findUserById(recip);

        model.addAttribute("recipient", ru);
        model.addAttribute("users", userDao.getOne(user_id));
        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("moneySaved", moneySaved);
        return "verification-form-create";
    }

    // Generate the form and send
    @PostMapping("/verification/{user_id}/form/send/{recip}")
    public String postVerificationFormSend(Model model, @PathVariable long user_id, @PathVariable long recip, @RequestParam String date) {
        SmokerInfo smokerInfo = smokeDao.getOne(user_id);
        int moneySaved = calcMoneySaved(smokerInfo.getCost_of_cigs_saved(), smokerInfo.getTotal_days_smoke_free());

        // Find recipients username (ru)
        User ru = userDao.findUserById(recip);

        // Convert user id to int for constructor
        int uid = Integer.parseInt(String.valueOf(user_id));

        // Create verification and notification
        Verification v = new Verification(uid, ru.getUsername(), date, 1, false);

        veriDao.save(v);

        model.addAttribute("smoke", smokerInfo);
        model.addAttribute("moneySaved", moneySaved);
        return "redirect:/verification/";
    }

}
