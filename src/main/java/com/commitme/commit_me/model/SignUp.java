package com.commitme.commit_me.model;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;


    public SignUp() {
    }


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


    public User getUser() {
        return user; }

    public void setUser(User user) {
         this.user = user; }



    public Event getEvent() { 
        return event; }

    public void setEvent(Event event) {
        this.event = event; }




}
