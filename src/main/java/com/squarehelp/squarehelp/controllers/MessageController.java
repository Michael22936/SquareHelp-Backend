package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.*;
import com.squarehelp.squarehelp.repositories.MessagesRepository;
import com.squarehelp.squarehelp.repositories.NotificationRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import com.squarehelp.squarehelp.services.NotificationServices;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.squarehelp.squarehelp.util.UnreadNotifications.unreadNotificationsCount;

@Controller
public class MessageController {
    private final MessagesRepository messageDao;
    private final UserRepository userDao;
    private final SmokerInfoRepository smokeDao;
    private final NotificationServices notiServices;
    private final NotificationRepository notiDao;

    public MessageController(MessagesRepository messageDao, UserRepository userDao, SmokerInfoRepository smokeDao, NotificationServices notiServices, NotificationRepository notiDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
        this.smokeDao = smokeDao;
        this.notiServices = notiServices;
        this.notiDao = notiDao;
    }

    // View all messages and shows unique authors
    @GetMapping("/message")
    public String getSendMessageView(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        int unreadNotifications = unreadNotificationsCount(notiDao, id);

        // Get all messages from for current user and add unique messages to another list and pass it to the view.
        // Get all msgs
        List<Messages> msgs = messageDao.findDistinctByRecipient_user_idOrAuthor_user_id(id);
        ArrayList<MessagesUnique> temp = new ArrayList<MessagesUnique>();

        // Filter for only logged in user
        for(Messages m : msgs) {
            if (m.getRecipient_user_id() == id) {
                temp.add(new MessagesUnique(m.getRecipient_user_id(), userDao.findUserById((long) m.getRecipient_user_id()).getUsername()));
            } else if (m.getAuthor_user_id() == id) {
                temp.add(new MessagesUnique(m.getRecipient_user_id(), userDao.findUserById((long) m.getRecipient_user_id()).getUsername()));
            }
        }

        List<MessagesUnique> uniques = new ArrayList<MessagesUnique>();
        System.out.println("uniques.size() after declaration = " + uniques.size());
        System.out.println("all messages size:  msgs.size() = " + msgs.size());

        System.out.println("====================Whats in temp");
        for (MessagesUnique t : temp ) {
            System.out.println("t.getUsername() = " + t.getUsername());
        }

        // Create uniques and pass to view
        if (temp.size() > 0) {
            System.out.println("=====================for loop");
            for (int i = 0; i < temp.size(); i++) {
                System.out.println("-- i is " + i);

                if (uniques.size() == 0) {
                    System.out.println("- Adding because unique is 0");
                    uniques.add(temp.get(0));
                }

                System.out.println("uniques.size() = " + uniques.size());

                if (temp.get(i).getUsername().equals(user.getUsername())) {
                    System.out.println("- continuing because current user");
                    continue;
                }

                for (int j = 0; j < uniques.size(); j++) {
                    System.out.println("- testing " + temp.get(i).getUsername() + " with " + uniques.get(j).getUsername());

                    if (temp.get(i).getUsername().equals(uniques.get(j).getUsername())) {
                        System.out.println("- ignoring because usernames match");
                        break;
                    } else {
                        System.out.println(temp.get(i).getUsername() + " != " + uniques.get(j).getUsername());

                        if (temp.get(i).getUsername().equals(user.getUsername())) {
                            System.out.println("- skipping because is current user!");
                            break;
                        }

                        System.out.println("+ adding " + uniques.get(j).getUsername() + " to uniques.");
                        uniques.add(temp.get(i));
                        break;
                    }
                }
            }
        }

        System.out.println("=============================");
        System.out.println("temp.size() = " + temp.size());
        System.out.println("uniques.size() = " + uniques.size());

        model.addAttribute("uniqueMsgs", uniques);

        model.addAttribute("alertCount", unreadNotifications); // shows count for unread notifications
        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("users", userDao.getOne(id));

        return "message-view-all";
    }

    // View an conversation with one person
    @GetMapping("/message/view/{rId}")
    public String getSingleMessageView(Model model, @PathVariable long rId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        List<Messages> m = messageDao.findAll();

        // Filter this logged in user and specified recipient conversation only
        ArrayList<Messages> conversation = new ArrayList<Messages>();

        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getRecipient_user_id() == rId && m.get(i).getAuthor_user_id() == id) {
                conversation.add(m.get(i));
            } else if (m.get(i).getRecipient_user_id() == id && m.get(i).getAuthor_user_id() == rId) {
                conversation.add(m.get(i));
            }
        }

        model.addAttribute("conversation", conversation);
        model.addAttribute("recip", userDao.getOne(rId));
        model.addAttribute("msgs", m);

        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("users", user);

        return "message-view-one";
    }

    // View form to find user to create a new message
    @GetMapping("/message/create")
    public String getFindRecipPage(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("users", userDao.getOne(id));

        return "message-create";
    }

    // Form to actually compose message and send it
    @GetMapping("/message/{rId}/send")
    public String sendMessage(Model model, @PathVariable long rId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        int unreadNotifications = unreadNotificationsCount(notiDao, id);

        model.addAttribute("alertCount", unreadNotifications); // shows count for unread notifications
        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("recipient", userDao.getOne(rId));

        return "sendMessage";
    }

    @GetMapping("/message/{rId}")
    public String sendAMessageToAnotherUser(@PathVariable long rId, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();
        int unreadNotifications = unreadNotificationsCount(notiDao, id);

        model.addAttribute("smoke", smokeDao.getOne(id));
        model.addAttribute("alertCount", unreadNotifications); // shows count for unread notifications
        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("recipient", userDao.getOne(rId));

        return "sendMessage";
    }

    // Save new messages from the form to database
    @PostMapping("/message/{rId}/send")
    public String SaveMessage( @PathVariable long rId, @RequestParam String message) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        // get recip's username
        User recip = userDao.findUserById(rId);
        String recipUsername = recip.getUsername();

        // Create a new date object to update last_updated
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());

        messageDao.save(new Messages((int) id,(int) rId, message, user, recipUsername, sqlDate));
        notiServices.createNotification(user.getUsername(), rId, "msg");

        return "redirect:/message";
    }

    // Used by message-one-api.js to dynamically update page.
    @GetMapping("/message/view/{rId}/quick")
    @ResponseBody
    public List<Messages> jsonConversation (@PathVariable long rId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        List<Messages> m = messageDao.findDistinctByRecipient_user_idOrAuthor_user_id(rId);

        // Filter this logged in user and specified recipient conversation only
        ArrayList<Messages> conversation = new ArrayList<Messages>();

        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getRecipient_user_id() == rId && m.get(i).getAuthor_user_id() == user.getId()) {
                conversation.add(m.get(i));
            } else if (m.get(i).getRecipient_user_id() == user.getId() && m.get(i).getAuthor_user_id() == rId) {
                conversation.add(m.get(i));
            }
        }

        return conversation;
    }

    // Used by message-one-api.js to save responses
    @PostMapping("/message/view/{rId}/quick")
    public String jsonConversationSave (@PathVariable long rId, @RequestParam String message) {
        System.out.println("=========quickly saving message==========");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = user.getId();

        // get recip's username
        User recip = userDao.findUserById(rId);
        String recipUsername = recip.getUsername();

        // Create a new date object to update last_updated
        java.util.Date now = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());

        messageDao.save(new Messages((int) id,(int) rId, message, user, recipUsername, sqlDate));
        notiServices.createNotification(user.getUsername(), rId, "msg");

        return "redirect:/message/view/" + rId;
    }

    @GetMapping("/search")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseBody
    public List<User> sendMatchingUser(@RequestParam String username){
        System.out.println(username);
        System.out.println(userDao.findByUsernameContaining(username));
        return userDao.findByUsernameContaining(username);
    }
}


