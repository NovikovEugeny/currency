package by.bsuir.currency_project.service.impl;

import by.bsuir.currency_project.entity.User;
import by.bsuir.currency_project.repository.UserRepository;
import by.bsuir.currency_project.service.UserService;
import by.bsuir.currency_project.service.exception.DuplicateEmailException;
import by.bsuir.currency_project.service.security.SecurityService;
import by.bsuir.currency_project.service.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User signIn(String login, String password) {
        userValidator.validateSignIn(login, password);

        return securityService.autologin(login, password);
    }

    @Override
    public User signUp(User user) {
        userValidator.validateSignUp(user);

        if (userRepository.findUserByLogin(user.getLogin()) != null) {
            throw new DuplicateEmailException();
        }

        String password = user.getPassword();

        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);
        user.setBalance(0);

        userRepository.save(user);
        return securityService.autologin(user.getLogin(), password);
    }

    @Override
    public void addFunds(int id, double sum) {
        userValidator.validateAddFunds(sum);

        User user = userRepository.findOne(id);
        double balance = user.getBalance() + sum;
        user.setBalance(balance);

        userRepository.save(user);
    }
}
