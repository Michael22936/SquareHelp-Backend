package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.Messages;
import com.squarehelp.squarehelp.repositories.MessagesRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/send/message")
    public String getSendMessageView(){
        return "/message";
    }

    @PostMapping("/send/{id}/message")
    public String sendAMessageToAnotherUser(@PathVariable long id,
                                            @RequestParam String message,
                                            @RequestParam String recepiantUsername){
        Messages sendMessage = messageDao.getOne(id);
        sendMessage.setRecipient_user_id(recepiantUsername);
        sendMessage.setMessage(message);
        return "redirect:/profile/" + id;
    }
}
