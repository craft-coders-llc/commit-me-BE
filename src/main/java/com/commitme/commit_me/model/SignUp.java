package com.commitme.commit_me.model;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
        private LocalDateTime created_on;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id")
        private List<User> user;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "event_id")
        private List<Event> events;

        public SignUp () {
        }

        public Integer getId() {
            return this.id;
        }

        public void setId (Integer id) {
            this.id = id;
        }

        public LocalDateTime getDate() {
            return this.created_on;
        }
    
        public void setDate(LocalDateTime createdOn) {
            this.created_on = createdOn;
        }
        
    }
