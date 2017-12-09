package by.bsuir.currency_project.service;

import by.bsuir.currency_project.entity.Subscription;

public interface SubscriptionService {

    Subscription findCurrentSubscription(int id);

    void unsubscribe(int id);

    void subscribe(int id, String type);
}
