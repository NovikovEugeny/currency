package by.bsuir.currency_project.controller;

import by.bsuir.currency_project.entity.User;
import by.bsuir.currency_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController()
@CrossOrigin(/*"http://localhost:4200"*/)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/sign-in")
    public User signIn(@RequestBody User user) {
        return userService.signIn(user.getLogin(), user.getPassword());
    }

    @PostMapping("/sign-up")
    public User signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @GetMapping("/log-out")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
    }

    @PatchMapping("/addFunds")
    public void add(@RequestBody HashMap<String, Integer> data) {
        userService.addFunds(data.get("id"), data.get("sum"));
    }


}
