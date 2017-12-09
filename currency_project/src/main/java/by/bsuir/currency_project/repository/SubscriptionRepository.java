package by.bsuir.currency_project.repository;

import by.bsuir.currency_project.entity.Subscription;
import by.bsuir.currency_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    List<Subscription> findAllByIsActive(String str);

    Subscription findByUser(User user);

    Subscription findByUserAndIsActive(User user, String str);
}
