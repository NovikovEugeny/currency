package by.bsuir.currency_project.repository;

import by.bsuir.currency_project.entity.AnalyticsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsTypeRepository extends JpaRepository<AnalyticsType, String> {
}
