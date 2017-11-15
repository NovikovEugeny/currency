package by.bsuir.currency_project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "analytics_types")
public class AnalyticsType implements Serializable {

    private static final long serialVersionUID = -5601566283993391556L;

    private String type;
    private double cost;
    private Set<AnalyticsOrder> analyticsOrders;

    public AnalyticsType() {}

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

    @OneToMany(mappedBy = "analyticsType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<AnalyticsOrder> getAnalyticsOrders() {
        return analyticsOrders;
    }

    public void setAnalyticsOrders(Set<AnalyticsOrder> analyticsOrders) {
        this.analyticsOrders = analyticsOrders;
    }

    @Override
    public String toString() {
        return "AnalyticsType{" +
                "type='" + type + '\'' +
                ", cost=" + cost +
                ", analyticsOrders=" + analyticsOrders +
                '}';
    }
}
