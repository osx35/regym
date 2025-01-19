package com.example.data.repositories;

import com.example.data.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
    Gym findByName(String name);
    List<Gym> findByOpeningHourAfterAndClosingHourBefore(double openingHour, double closingHour);
}
