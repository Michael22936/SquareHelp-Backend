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

    @GetMapping("/send/{id}/message")
    public String getSendMessageView(Model model, @PathVariable long id){

        model.addAttribute("users", userDao.getOne(id));
        model.addAttribute("messages", messageDao.getOne(id));

        return "message";
    }

    @PostMapping("/send/{id}/message")
    public String sendAMessageToAnotherUser(@PathVariable long id,
                                            @RequestParam String message,
                                            @ModelAttribute Messages messages,
                                            @ModelAttribute User users){
//        List<Messages> listOfMessages = messageDao.findMessagesByRecipient_user_idIs(id);

        Messages sendMessage = messageDao.getOne(id);
        sendMessage.setMessage(message);
        messageDao.save(new Messages((int) id, messages.getRecipient_user_id(), message));
        return "redirect:/profile/" + id;
    }

//    ================= Matt built :)
    @PostMapping("/search/{id}/message")
    public String FindUser(@PathVariable long id,
                           @ModelAttribute User users,
                           @RequestParam String recipientUser,
                           Model model){
        //        ============================= Search +++++++++
        List<User> searchResults;
        searchResults = userDao.findByUsernameContaining(recipientUser);

        //        ====================== // For TESTING \\ ============================
        int counter = 1;
        for (User user: searchResults) {
            System.out.println("user " + counter + " = " + user.getUsername());
            counter++;
        }

//        ======================================================================

        model.addAttribute("ListOfUsers", searchResults);
        return "redirect:/send/" + id + "/message";
    }

}
