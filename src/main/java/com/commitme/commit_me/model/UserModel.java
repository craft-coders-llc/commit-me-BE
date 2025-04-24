package com.commitme.commit_me.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

public class UserModel {

    @Entity
    @Table(name = "users")
    public class User {
        @Id
        @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1, initialValue = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
        private Integer id;

        @Column
        private String userName;

        @Column
        private String email;

        @Column
        private String password;

        @Column
        String imagePath = "path/to/your/image.jpg";

        public User() {
        }

        public Integer getId() {
            return this.id;
        }
    
        public void setId(Integer id) {
            this.id = id;
        }
    
        public String getName() {
            return this.userName;
        }
    
        public void setName(String userName) {
            this.userName = userName;
        }
    
        public String getEmail() {
            return this.email;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return this.password;
        }
    
        public void setPassword(String password) {
            this.password = password;
        }

        public String getImagePath() {
            return this.imagePath;
        }
    
        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

    }

}
