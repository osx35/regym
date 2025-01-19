package com.example.data.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Data
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    @ManyToMany(mappedBy = "equipments", fetch = FetchType.LAZY)
    private List<Gym> gyms;
    private String condition;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
//    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public Equipment(Long id, String name, String type, List<Gym> gyms, String condition, Exercise exercise) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.gyms = gyms;
        this.condition = condition;
        this.exercise = exercise;
    }

    public Equipment(){}
}
