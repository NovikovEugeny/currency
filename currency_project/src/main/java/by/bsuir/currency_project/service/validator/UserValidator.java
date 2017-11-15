package by.bsuir.currency_project.service.validator;

import by.bsuir.currency_project.entity.User;
import by.bsuir.currency_project.service.exception.BadRequestException;
import by.bsuir.currency_project.service.validator.regexp.RegExp;
import by.bsuir.currency_project.service.validator.regexp.RegexpTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public final class UserValidator {

    @Autowired
    private RegexpTester regexpTester;

    public void validateSignIn(String login, String password) {
       if (login == null || login.isEmpty()) {
           throw new BadRequestException();
       }
       if (password == null || password.isEmpty()) {
           throw new BadRequestException();
       }
    }

    public void validateSignUp(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new BadRequestException();
        }
        if (!regexpTester.test(RegExp.NAME_REG_EXP, user.getName())) {
            throw new BadRequestException();
        }
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new BadRequestException();
        }
        if (!regexpTester.test(RegExp.EMAIL_REG_EXP, user.getLogin())) {
            throw new BadRequestException();
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException();
        }
        if (!regexpTester.test(RegExp.PASSWORD_REG_EXP, user.getPassword())) {
            throw new BadRequestException();
        }
    }

    public void validateAddFunds(double sum) {
        if (sum <= 0) {
            throw new BadRequestException();
        }
    }



}
