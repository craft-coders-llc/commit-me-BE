package com.commitme.commit_me.model;

import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "event")

public class Event {

    @Id
    @SequenceGenerator(name = "event_id_sequence", sequenceName = "event_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_id_sequence")
    private Integer id;

    @Column
    @NotBlank(message = "(!) ERROR: el campo del título no puede estar vacío")
    @Size(max = 50, message = "(!) ERROR: el campo del título no puede tener más de 50 caracteres")
    private String title;

    @Column
    @NotBlank(message = "(!) ERROR: el campo de la descripción no puede estar vacío")
    @Size(max = 500, message = "(!) ERROR: el campo del título no puede tener más de 500 caracteres")
    private String description;

    @Column
    @NotBlank(message = "(!) ERROR: el campo de fecha no pude estar vacío")
    private String date;

    @Column
    @NotBlank(message = "(!) ERROR: el campo de hora no pude estar vacío")
    private String time;

    @Column
    @NotBlank(message = "(!) ERROR: el campo de la dirección no puede estar vacío")
    @Size(max = 100, message = "(!) ERROR: el campo de la dirección no puede tener más de 100 caracteres")
    private String venue;

    @Column
    @NotNull(message = "(!) ERROR: el campo del máximo de participantes no puede estar vacío")
    @Min(value = 1, message = "(!) ERROR: el campo del máximo de participantes debe tener un valor mínimo de 1")
    private Integer maxAttendees;

    @Column
    private String image;

    @CreationTimestamp
    private LocalDateTime eventCreated_on;

    @UpdateTimestamp
    private LocalDateTime eventUpdated_on;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @OneToMany(mappedBy = "event")
    private List<SignUp> signUps;

    public Event() {
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setMaxAttendees(Integer maxAttendees){
        this.maxAttendees = maxAttendees;
    }

    public Integer getMaxAttendees(){
        return this.maxAttendees;
    }

    public LocalDateTime getEventUpdated_on() {
        return this.eventUpdated_on;
    }

    public void setEventUpdated_on(LocalDateTime eventUpdated_on) {
        this.eventUpdated_on = eventUpdated_on;
    }

}
