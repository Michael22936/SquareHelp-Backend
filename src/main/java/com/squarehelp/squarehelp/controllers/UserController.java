


//package com.squarehelp.squarehelp.controllers;
//
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController("/")
//public class UserController {
//
//
//
//
//}


//============================ Example Code ===========================
//=== CONTROLLER ===
// File path:   /PeopleController.java
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@RequestMapping("/people")
//public class PeopleController {
//
//    @Autowired
//    private PeopleService peopleService;
//
//    @RequestMapping(method = RequestMethod.GET)
//    public Collection<Person> getAllPeople() {
//        return peopleService.getAllPeople();
//    }
//
//}

//=== SERVICE ===
// File path:   /PeopleController.java
//@Service
//public class PeopleService {
//
//    @Autowired
//    private PersonDao personDao;
//
//    public Collection<Person> getAllPeople() {
//        return personDao.getAllPeople();
//    }
//
//
//}


//=== ENTITY ===
// File path:   /PeopleController.java
//public class Person {
//
//    private int id;
//    private String name;
//
//    public Person(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public Person() {};
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}