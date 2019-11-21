package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.Messages;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.MessagesRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
    private final MessagesRepository messageDao;
    private final SmokerInfoRepository smokeDao;
    private final UserRepository userDao;

    public MessageController(MessagesRepository messageDao, SmokerInfoRepository smokeDao, UserRepository userDao){
        this.messageDao = messageDao;
        this.smokeDao = smokeDao;
        this.userDao = userDao;
    }

    @GetMapping("/send/{id}/message")
    public String getSendMessageView(Model model, @PathVariable long id){

        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("messages", messageDao.getOne(id));

        return "message";
    }

    @PostMapping("/send/{id}/message")
    public String sendAMessageToAnotherUser(@PathVariable long id,
                                            @RequestParam String message,
                                            @RequestParam String recipeantUsername){
        User user = userDao.findByUsername(recipeantUsername);
        Messages sendMessage = messageDao.getOne(id);
        sendMessage.setRecipient_user_id(String.valueOf(user.getId()));
        sendMessage.setMessage(message);
        messageDao.save(new Messages("1",recipeantUsername,message));
        return "redirect:/profile/" + id;
    }
}
