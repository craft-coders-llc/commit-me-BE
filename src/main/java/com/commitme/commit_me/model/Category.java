package com.commitme.commit_me.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @SequenceGenerator(name = "category_id_sequence", sequenceName = "category_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_sequence")
    private Integer id;

    @Column
    @NotBlank(message = "(!) ERROR: el campo del tipo de categoría no puede estar nulo")
    @Size(max = 20)
    @Pattern(regexp = "^[^\\/*<>|]+$", message = "(!) ERROR: No está permitido el uso de caracteres especiales")
    private String type;

    @OneToMany(mappedBy = "category") // tiene que ser null sin no hay categoria //comming soon si el espacio es null
    private List<Event> events;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Category() {
    }

}
