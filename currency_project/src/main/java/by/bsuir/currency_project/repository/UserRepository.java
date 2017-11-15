package by.bsuir.currency_project.repository;

import by.bsuir.currency_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByLogin(String login);

}
