package by.bsuir.currency_project.service.util.validator;

import by.bsuir.currency_project.entity.User;
import by.bsuir.currency_project.service.exception.BadRequestException;
import by.bsuir.currency_project.service.util.validator.regexp.RegExp;
import by.bsuir.currency_project.service.util.validator.regexp.RegExpTester;


public final class UserValidator {

    public static void validateSignIn(String login, String password) {
       if (login == null || login.isEmpty()) {
           throw new BadRequestException();
       }
       if (password == null || password.isEmpty()) {
           throw new BadRequestException();
       }
    }

    public static void validateSignUp(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExpTester.test(RegExp.NAME_REG_EXP, user.getName())) {
            throw new BadRequestException();
        }
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExpTester.test(RegExp.EMAIL_REG_EXP, user.getLogin())) {
            throw new BadRequestException();
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BadRequestException();
        }
        if (!RegExpTester.test(RegExp.PASSWORD_REG_EXP, user.getPassword())) {
            throw new BadRequestException();
        }
    }

    public static void validateAddFunds(double sum) {
        if (sum <= 0) {
            throw new BadRequestException();
        }
    }



}
