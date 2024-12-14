package DealerShop.controller;

import DealerShop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name,
                           @RequestParam String login,
                           @RequestParam String password,
                           @RequestParam String email) {
        userService.registerUser(name, login, password, email);
        return "redirect:/login";
    }

}
