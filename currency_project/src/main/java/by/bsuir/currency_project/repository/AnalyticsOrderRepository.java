package by.bsuir.currency_project.repository;

import by.bsuir.currency_project.entity.AnalyticsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsOrderRepository extends JpaRepository<AnalyticsOrder, Integer> {
}
