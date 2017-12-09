package by.bsuir.currency_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 3124535843065898094L;

    private int id;
    private String name;
    private String login;
    private String password;
    private double balance;
    @JsonIgnore
    private Subscription subscription;
    @JsonIgnore
    private Set<AnalyticsOrder> analyticsOrders;

    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @OneToOne(mappedBy = "user")
    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<AnalyticsOrder> getAnalyticsOrders() {
        return analyticsOrders;
    }

    public void setAnalyticsOrders(Set<AnalyticsOrder> analyticsOrders) {
        this.analyticsOrders = analyticsOrders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", subscription=" + subscription +
                ", analyticsOrders=" + analyticsOrders +
                '}';
    }
}
