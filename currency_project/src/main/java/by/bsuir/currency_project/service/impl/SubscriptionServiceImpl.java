package by.bsuir.currency_project.service.impl;

import by.bsuir.currency_project.entity.Subscription;
import by.bsuir.currency_project.entity.SubscriptionType;
import by.bsuir.currency_project.entity.User;
import by.bsuir.currency_project.repository.SubscriptionRepository;
import by.bsuir.currency_project.repository.SubscriptionTypeRepository;
import by.bsuir.currency_project.repository.UserRepository;
import by.bsuir.currency_project.service.SubscriptionService;
import by.bsuir.currency_project.service.exception.ConflictException;
import by.bsuir.currency_project.service.util.MailMessenger;
import by.bsuir.currency_project.service.util.validator.SubscriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    MailSender mailSender;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionTypeRepository subscriptionTypeRepository;

    @Override
    public Subscription findCurrentSubscription(int id) {
        User user = new User();
        user.setId(id);
        return subscriptionRepository.findByUserAndIsActive(user, "yes");
    }

    @Override
    public void unsubscribe(int id) {
        User user = new User();
        user.setId(id);
        Subscription subscription = subscriptionRepository.findByUserAndIsActive(user, "yes");
        subscription.setIsActive("no");
        subscriptionRepository.save(subscription);
    }

    @Transactional
    @Override
    public void subscribe(int id, String type) {
        SubscriptionValidator.validateSubscription(type);

        User user = userRepository.findOne(id);
        SubscriptionType subscriptionType = subscriptionTypeRepository.findOne(type);

        if (user.getBalance() < subscriptionType.getCost()) {
            throw new ConflictException();
        }

        Subscription subscription = subscriptionRepository.findByUser(user);

        if (subscription != null) {
            subscription.setSubscriptionType(subscriptionType);
            subscription.setSubscriptionDate(new Date());
            subscription.setEndDate(endDate(type));
            subscription.setIsActive("yes");

        } else {
            subscription = new Subscription();
            subscription.setUser(user);
            subscription.setSubscriptionType(subscriptionType);
            subscription.setSubscriptionDate(new Date());
            subscription.setEndDate(endDate(type));
            subscription.setIsActive("yes");
        }

        subscriptionRepository.save(subscription);

        double balance = user.getBalance();
        double newBalance = balance - subscriptionType.getCost();
        user.setBalance(newBalance);

        userRepository.save(user);
    }

    @Scheduled(fixedRate = 60000)//cутки 86400000
    public void notifyUser() {

        final String url = "http://www.nbrb.by/API/ExRates/Rates/145";

        Map<String, Object> map = new RestTemplate().getForObject(url, Map.class);
        double currentRate = (Double) map.get("Cur_OfficialRate");

        List<Subscription> subscriptions = subscriptionRepository.findAllByIsActive("yes");

        for (Subscription subscription : subscriptions) {
            if (new Date().after(subscription.getEndDate())) {
                subscription.setIsActive("no");
                subscriptionRepository.save(subscription);
            } else {
                mailSender.send(MailMessenger.getMessenger(subscription.getUser().getLogin(),
                        "Current rate: " + currentRate));
            }
        }
    }

    private Date endDate(String period) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());

        switch (period) {
            case "week":
                instance.add(Calendar.WEEK_OF_MONTH, 1);
                break;
            case "month":
                instance.add(Calendar.MONTH, 1);
                break;
            case "year":
                instance.add(Calendar.YEAR, 1);
                break;
        }
        return instance.getTime();
    }

}
