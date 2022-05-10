package com.pp.app.controllers;

import com.pp.app.models.User;
import com.pp.app.repositories.UserRepo;
import com.pp.app.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Controller
public class UserController {
    @Value("${upload.path}")
    private String uploadpath;
    private final UserRepo userRepo;
    private final UserService userService;

    public UserController(UserRepo userRepo, UserService userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/login")
    private String login(){
        return "login";
    }

    @GetMapping("/registration")
    private String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    private String createUser(User user, Model model,
                              @RequestParam("avatar") MultipartFile profilePhoto,
                              Principal principal) throws IOException {
        if (profilePhoto != null) {
            File uploadDir = new File(uploadpath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); }
            String uuidFile = UUID.randomUUID().toString();
            String resultname = uuidFile + "." + profilePhoto.getOriginalFilename();
            profilePhoto.transferTo(new File(uploadDir + "/" + resultname));
            user.setAvatarFilename(resultname);
        }
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "User with this email already exist in system");
            return "registration";
        }
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    private String getAnotherUser(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "userInfo";
    }
}
