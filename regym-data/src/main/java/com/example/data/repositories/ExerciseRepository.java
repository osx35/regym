package com.example.data.repositories;

import com.example.data.enums.TargetMuscle;
import com.example.data.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByTargetMuscle(TargetMuscle targetMuscle);
}
