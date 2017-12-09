package by.bsuir.currency_project.repository;

import by.bsuir.currency_project.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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

    @Test
    public void test() {
        List response = new RestTemplate().getForObject("http://localhost:3000/get-7", List.class);

        System.out.println("value: " + response.get(2));
    }

    @Test
    public void test2() {
        User user = new User();
        user.setId(4);
        System.out.println(subscriptionRepository.findByUserAndIsActive(user, "yes"));
    }

}
