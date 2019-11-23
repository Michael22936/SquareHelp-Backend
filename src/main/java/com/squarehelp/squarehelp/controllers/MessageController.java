package com.squarehelp.squarehelp.controllers;

import com.squarehelp.squarehelp.models.Messages;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.MessagesRepository;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {
    private final MessagesRepository messageDao;
    private final UserRepository userDao;

    public MessageController(MessagesRepository messageDao, UserRepository userDao){
        this.messageDao = messageDao;
        this.userDao = userDao;
    }

    @GetMapping("/message/{id}")
    public String getSendMessageView(Model model, @PathVariable long id){


        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("messages", messageDao.getOne(id));

        return "message";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<User> sendMatchingUser(@RequestParam String username){
        System.out.println(userDao.findByUsernameContaining(username));
        return userDao.findByUsernameContaining(username);
    }



    @GetMapping("/message/{rId}/{aId}")
    public String sendAMessageToAnotherUser(@PathVariable long rId,
                                            @PathVariable String aId

                                            ){


        return "redirect:/message/" + rId + "/" + aId + "/send";
    }

    @GetMapping("/message/{rId}/{aId}/send")
    public String sendMessage( Model model,
                               @PathVariable long rId,
                               @PathVariable String aId){

        model.addAttribute("user", userDao.getOne(Long.parseLong(aId)));
        model.addAttribute("recipient", userDao.getOne(rId));
        return "sendMessage";
    }

    @PostMapping("/message/{rId}/{aId}/send")
    public String SaveMessage( @PathVariable long rId,
                               @PathVariable String aId,
                               @RequestParam String message
                               ){

                messageDao.save(new Messages(Integer.parseInt(aId),(int) rId, message));
        return "redirect:/profile/" + aId;
    }


}
