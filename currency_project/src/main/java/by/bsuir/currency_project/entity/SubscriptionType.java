package by.bsuir.currency_project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "subscription_types")
public class SubscriptionType implements Serializable {

    private static final long serialVersionUID = 4164906484234569875L;

    private String type;
    private double cost;
    private Set<Subscription> subscriptions;

    public SubscriptionType() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @OneToMany(mappedBy = "subscriptionType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "SubscriptionType{" +
                "type='" + type + '\'' +
                ", cost=" + cost +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
