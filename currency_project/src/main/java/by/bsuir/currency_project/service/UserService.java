package by.bsuir.currency_project.service;

import by.bsuir.currency_project.entity.User;

public interface UserService {

    User signIn(String login, String password);

    User signUp(User user);

    void addFunds(int id, double sum);

}