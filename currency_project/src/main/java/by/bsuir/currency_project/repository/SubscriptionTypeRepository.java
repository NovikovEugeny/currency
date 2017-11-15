package by.bsuir.currency_project.repository;

import by.bsuir.currency_project.entity.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, String> {
}
