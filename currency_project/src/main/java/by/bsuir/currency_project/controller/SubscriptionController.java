package by.bsuir.currency_project.controller;

import by.bsuir.currency_project.entity.Subscription;
import by.bsuir.currency_project.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/get-subscription/user-id/{id}")
    public Subscription getSubscription(@PathVariable int id) {
        return subscriptionService.findCurrentSubscription(id);
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestBody Map<String, String> data) {
        subscriptionService.subscribe(Integer.parseInt(data.get("id")), data.get("type"));
    }

    @PatchMapping("/unsubscribe/user-id/{id}")
    public void unsubscribe(@PathVariable int id) {
        subscriptionService.unsubscribe(id);
    }

}
