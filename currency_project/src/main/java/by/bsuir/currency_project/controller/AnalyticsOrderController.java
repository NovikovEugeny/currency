package by.bsuir.currency_project.controller;

import by.bsuir.currency_project.service.AnalyticsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AnalyticsOrderController {

    @Autowired
    private AnalyticsOrderService analyticsOrderService;

    @PostMapping("/order-analytics")
    public List<Double> order(@RequestBody Map<String, String> data) {
        return analyticsOrderService.order(Integer.parseInt(data.get("id")), data.get("type"));
    }

}
