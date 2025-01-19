package com.example.data.model;

import com.example.data.enums.TargetMuscle;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private TargetMuscle targetMuscle;
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<Equipment> equipments;

    public Exercise(Long id, String name, String description, TargetMuscle targetMuscle, List<Equipment> equipments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.targetMuscle = targetMuscle;
        this.equipments = equipments;
    }

    public Exercise(){}
}
