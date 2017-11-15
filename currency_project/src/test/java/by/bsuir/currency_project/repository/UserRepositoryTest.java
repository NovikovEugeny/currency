package by.bsuir.currency_project.repository;

import by.bsuir.currency_project.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UserRepositoryTest {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;



    @Test
    public void findTest() {

        User user = userRepository.findOne(7);
        System.out.println(user);

        user.setBalance(100);
        userRepository.save(user);

        System.out.println(userRepository.findOne(7));
    }

}
