package by.bsuir.currency_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
public class Subscription implements Serializable {

    private static final long serialVersionUID = 335286710320304845L;

    private int id;
    @JsonIgnore
    private User user;
    private SubscriptionType subscriptionType;
    private Date subscriptionDate;
    private Date endDate;
    private String isActive;

    public Subscription() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "type")
    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    @Column(name = "subscription_date")
    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "is_active")
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", subscriptionType=" + subscriptionType.getType() +
                ", subscriptionDate=" + subscriptionDate +
                ", endDate=" + endDate +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}
