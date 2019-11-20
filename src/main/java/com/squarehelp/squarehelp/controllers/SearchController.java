package com.squarehelp.squarehelp.controllers;



import com.google.gson.Gson;
import com.squarehelp.squarehelp.models.SmokerInfo;
import com.squarehelp.squarehelp.models.User;
import com.squarehelp.squarehelp.repositories.SmokerInfoRepository;
import com.squarehelp.squarehelp.repositories.UserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final UserRepository userDao;
    private final SmokerInfoRepository smokeDao;

    public SearchController(UserRepository userDao, SmokerInfoRepository smokeDao) {
        this.userDao = userDao;
        this.smokeDao = smokeDao;
    }

    // Used to convert user object to JSON
    // Just returns users.
//    private String convertUserToJson(User u, SmokerInfo s) {
//        // Create output collection
//        Map<String, String> temp = new HashMap<>();
//
//
//        // Add values to temp object
//        temp.put("isValidUser", "true");
//        temp.put("username", u.getUsername());
//        temp.put("gender", u.getGender());
//        temp.put("total_days_smoke_free", String.valueOf(s.getTotal_days_smoke_free()));
////        temp.put("email", u.getEmail());
////        temp.put("city", u.getCity());
////        temp.put("state", u.getState());
////        temp.put("dob", String.valueOf(u.getDob()));
////        temp.put("phone", u.getPhoneNumber());
////        temp.put("dateCreated", String.valueOf(u.getDateCreated()));
//
//        // Create new Gson object and add contents and convert to Json
//        Gson g = new Gson();
//        String out = g.toJson(temp);
//
//        return out;
//    }


//    private String convertOneUserToJson(List<User> listOfUsers2) throws JSONException {
//        //        Add each users json object into array list
//        ArrayList<String> ArrayOfJSONUsers = new ArrayList<>();
//
//        for (User u : listOfUsers2){
//            // Create output collection
//            Map<String, String> temp = new HashMap<>(); // 1
////        JSONObject jsonObj= new JSONObject();
//            SmokerInfo s = smokeDao.getOne(u.getId()); // Returns users smoke info with user id.
//
//            // Add values to temp object
//            temp.put("isValidUser", "true");
//            temp.put("username", u.getUsername());
//            temp.put("gender", u.getGender());
//            temp.put("total_days_smoke_free", String.valueOf(s.getTotal_days_smoke_free()));
//
////            temp.put("Username" + String.valueOf(count), u.getUsername()); This works!
//
//            // Create new Gson object and add contents and convert to Json
//            Gson g = new Gson(); // 1
//            String out = g.toJson(temp); // 1
//            System.out.println("temp JSON Obj = " + out);
//            ArrayOfJSONUsers.add(out); // Add each user Json obj to arraylist
//        }
// Convert arraylist of json objects to JSON
//        Gson g = new Gson(); // 1
////        String UsersInJson = g.toJsonTree(ArrayOfJSONUsers); // 1
//        System.out.println("UsersInJson = " + UsersInJson);
//
//        return UsersInJson;
//    }

    // If user not found, send frontend json false value.
//    private String convertUserToJson(Boolean isValidUser) {
//        if (isValidUser == false) {
//            Map<String, String> temp = new HashMap<>();
//
//            temp.put("isValidUser", "false");
//
//            // Create new Gson object and add contents and convert to Json
//            Gson g = new Gson();
//            String out = g.toJson(temp);
//
//            return out;
//        }
//        else {
//            return "Call this method with a User as a parameter";
//        }
//    }

    @RequestMapping(method = RequestMethod.GET)
    public String searchUser() {
        return "Please send a post request to this address";
    }

    @RequestMapping(method = RequestMethod.POST)
    public List<User> searchUser(@RequestParam String query) {
        return userDao.findByUsernameContaining(query);
//
//
//        if (listOfUsers2 != null){
//            String usersInJSOn = convertOneUserToJson(listOfUsers2);
//            System.out.println("Return in POst = " + usersInJSOn);
//
////            String userStrings = usersInJSOn.replace(" ", "");
////            System.out.println("JSON after cleaning = " + userStrings);
//                return usersInJSOn;
//        }else {
////            JSONArray jsonArray2 = new JSONArray();
////            String isValidUser = convertUserToJson(false); // Return isValidUser is false JSON
////            jsonArray2.put(isValidUser);
////            return jsonArray2;
//            return "Fail!";
//
//        }

    }



}
