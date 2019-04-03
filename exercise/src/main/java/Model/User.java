package Model;

import java.util.Collection;
import java.util.List;

public class User {
    private Integer id;
    private Enum Mood;
    private Double credit;
    private Collection<String> notificationChannel;
    private Collection<Product> history;

    public User(Enum mood, Double credit, Collection<String> notificationChannel, Collection<Product> history) {
        this(null, mood, credit, notificationChannel, history);
    }

    public User(Integer id, Enum mood, Double credit, Collection<String> notificationChannel, Collection<Product> history) {
        this.id = id;
        Mood = mood;
        this.credit = credit;
        this.notificationChannel = notificationChannel;
        this.history = history;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Enum getMood() {
        return Mood;
    }

    public void setMood(Enum mood) {
        Mood = mood;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Collection<String> getNotificationChannel() {
        return notificationChannel;
    }

    public void setNotificationChannel(Collection<String> notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public Collection<Product> getHistory() {
        return history;
    }

    public void setHistory(Collection<Product> history) {
        this.history = history;
    }
}
