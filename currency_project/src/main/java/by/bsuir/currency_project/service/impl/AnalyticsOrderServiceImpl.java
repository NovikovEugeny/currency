package by.bsuir.currency_project.service.impl;

import by.bsuir.currency_project.entity.AnalyticsOrder;
import by.bsuir.currency_project.entity.AnalyticsType;
import by.bsuir.currency_project.entity.User;
import by.bsuir.currency_project.repository.AnalyticsOrderRepository;
import by.bsuir.currency_project.repository.AnalyticsTypeRepository;
import by.bsuir.currency_project.repository.UserRepository;
import by.bsuir.currency_project.service.AnalyticsOrderService;
import by.bsuir.currency_project.service.exception.ConflictException;
import by.bsuir.currency_project.service.util.validator.AnalyticsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings(value = "unchecked")
public class AnalyticsOrderServiceImpl implements AnalyticsOrderService {

    @Autowired
    private AnalyticsOrderRepository analyticsOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnalyticsTypeRepository analyticsTypeRepository;

    @Transactional
    @Override
    public List<Double> order(int id, String type) {
        AnalyticsValidator.validateOrder(type);

        User user = userRepository.findOne(id);

        AnalyticsType analyticsType = analyticsTypeRepository.findOne(type);

        if (user.getBalance() < analyticsType.getCost()) {
            throw new ConflictException();
        }

        AnalyticsOrder analyticsOrder = new AnalyticsOrder();
        analyticsOrder.setUser(user);
        analyticsOrder.setAnalyticsType(analyticsType);
        analyticsOrder.setDate(new Date());

        analyticsOrderRepository.save(analyticsOrder);

        double oldBalance = user.getBalance();
        double newBalance = oldBalance - analyticsType.getCost();
        user.setBalance(newBalance);

        userRepository.save(user);

        return new RestTemplate().getForObject("http://localhost:3000/get-" + type, List.class);
    }
}
