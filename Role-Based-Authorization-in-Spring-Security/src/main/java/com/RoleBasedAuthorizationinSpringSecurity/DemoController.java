package com.RoleBasedAuthorizationinSpringSecurity;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DemoController {

    private final UserService userService;

    public DemoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "login";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model) {

        if (error != null) {
            model.addAttribute("errorMsg", "❌ Invalid username or password");
        }

        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {

        userService.registerUser(username, password);
        return "redirect:/login";
    }

    // 🔥 USER PAGE
    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    // 🔥 ADMIN PAGE
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    // 🔥 DEFAULT LANDING (optional)
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}