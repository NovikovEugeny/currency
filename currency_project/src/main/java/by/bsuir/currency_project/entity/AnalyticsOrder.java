package by.bsuir.currency_project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "analytics_orders")
public class AnalyticsOrder implements Serializable {

    private static final long serialVersionUID = 6409122181648737489L;

    private int id;
    private User user;
    private AnalyticsType analyticsType;
    private Date date;

    public AnalyticsOrder() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "type")
    public AnalyticsType getAnalyticsType() {
        return analyticsType;
    }

    public void setAnalyticsType(AnalyticsType analyticsType) {
        this.analyticsType = analyticsType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AnalyticsOrder{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", analyticsType=" + analyticsType.getType() +
                ", date=" + date +
                '}';
    }
}
