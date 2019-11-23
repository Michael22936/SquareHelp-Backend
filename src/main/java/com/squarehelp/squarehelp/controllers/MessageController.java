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

//    @PostMapping("/message/{rId}")
//    public String SaveMessage( @PathVariable long rId,
//                               @RequestParam String aId,
//                               @ModelAttribute Messages messages,
//                               @ModelAttribute User users){
////        messageDao.save(new Messages( (int)aId, rID, message));
//    }

    @GetMapping("/message/{rId}/{aId}")
    public String sendAMessageToAnotherUser(@PathVariable long rId,
                                            @PathVariable String aId

                                            ){
//        List<Messages> listOfMessages = messageDao.findMessagesByRecipient_user_idIs(id);
        System.out.println("aId = " + aId);
        System.out.println("rId = " + rId);
//        System.out.println("message = " + message);
//        Messages sendMessage = messageDao.getOne(id);
//        sendMessage.setMessage(message);
//        messageDao.save(new Messages(Integer.parseInt(aId),(int) rId, message));
        return "redirect:/profile/" + aId;
    }



}
