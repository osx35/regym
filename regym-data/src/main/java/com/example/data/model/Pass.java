package com.example.data.model;

import com.example.data.enums.PassType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "pass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;
    @Enumerated(EnumType.STRING)
    private PassType passType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double cost;

    public Pass(Long id, User user, PassType passType, LocalDateTime startDate, LocalDateTime endDate, Double cost) {
        this.id = id;
        this.user = user;
        this.passType = passType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public Pass(){}
}
