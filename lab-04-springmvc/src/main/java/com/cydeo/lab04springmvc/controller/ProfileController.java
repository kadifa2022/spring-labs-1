package com.cydeo.lab04springmvc.controller;

import com.cydeo.lab04springmvc.model.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String retrieveProfile(Model model){

        Profile profile= new Profile();//get the names through object
        profile.setName("James");//set the all value data
        profile.setSurname("Bond");
        profile.setUserName("007");
        profile.setEmail("jamesbond007@gmail.com");
        profile.setPhoneNumber("007007007");
        profile.setCreatedDate(LocalDateTime.now());
        model.addAttribute("profile", profile);
        return "profile/profile-info";
    }

}
