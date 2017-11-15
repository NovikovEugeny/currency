package by.bsuir.currency_project.controller;

import by.bsuir.currency_project.entity.User;
import by.bsuir.currency_project.service.UserService;
import by.bsuir.currency_project.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
@CrossOrigin(/*"http://localhost:4200"*/)
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/")
    public String start() {
        System.out.println("/get-logout");
        return "hello angular 4";
    }

    @GetMapping("/get-user")
    public User start2() {
        User user = new User();

        user.setName("Abdul");
        user.setLogin("abdul@mail.com");
        user.setBalance(2);

        return user;

    }

    @GetMapping("/secured")
    public String enter() {
        return securityService.findLoggedInUsername();
    }

    @GetMapping("/isAuth")
    public String test(HttpServletRequest request) {
        System.out.println(request.getRequestURL());
        return !SecurityContextHolder.getContext().getAuthentication().getCredentials().toString().equals("") + "!";
    }

}
