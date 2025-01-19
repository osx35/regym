package com.example.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Double openingHour;
    private Double closingHour;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "gym_equipment",
            joinColumns = @JoinColumn(name = "gym_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    List<Equipment> equipments;

    public Gym(Long id, String name, String address, String phone, Double openingHour, Double closingHour, List<Equipment> equipments) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.equipments = equipments;
    }

    public Gym(){}
}
