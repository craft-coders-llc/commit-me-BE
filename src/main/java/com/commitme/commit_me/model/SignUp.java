package com.commitme.commit_me.model;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "signUp")
public class SignUp {
    @Id
    @SequenceGenerator(name = "signUp_id_sequence", sequenceName = "signUp_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "signUp_id_sequence")
    private Integer id;

    @CreationTimestamp
    private LocalDateTime signUpCreated_on;

    @ManyToMany
    @JoinColumn(name = "user_id")
    private List<User> users;

    @ManyToMany
    @JoinColumn(name = "event_id")

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getSignUpCreated_on() {
        return this.signUpCreated_on;
    }

    public void setSignUpCreated_on(LocalDateTime signUpCreated_on) {
        this.signUpCreated_on = signUpCreated_on;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    private List<Event> events;

    public SignUp() {
    }

}
