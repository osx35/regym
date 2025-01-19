package com.example.webapi.services;

import com.example.data.model.Exercise;
import com.example.data.repositories.ExerciseRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise getExerciseById(Long id) {
        if(!exerciseRepository.existsById(id)){
            throw new IllegalArgumentException("Could not find exercise with id " + id);
        }
        return exerciseRepository.findById(id).get();
    }

    public Exercise updateExercise(Long updatedExerciseId, Exercise updatedExercise) {
        Exercise existingExercise = getExerciseById(updatedExerciseId);

        if(!existingExercise.getName().equals(updatedExercise.getName())){
            existingExercise.setName(updatedExercise.getName());
        }

        if(!existingExercise.getDescription().equals(updatedExercise.getDescription())){
            existingExercise.setDescription(updatedExercise.getDescription());
        }

        if(!existingExercise.getTargetMuscle().equals(updatedExercise.getTargetMuscle())){
            existingExercise.setTargetMuscle(updatedExercise.getTargetMuscle());
        }

        if(!existingExercise.getEquipments().equals(updatedExercise.getEquipments())){
            existingExercise.setEquipments(updatedExercise.getEquipments());
        }

        return exerciseRepository.save(existingExercise);
    }

    public void deleteExerciseById(Long id) {
        if(!exerciseRepository.existsById(id)){
            throw new IllegalArgumentException("Could not find and delete exercise with id " + id);
        }
        exerciseRepository.deleteById(id);
    }

    public List<Exercise> getAllExercises() {
        if(exerciseRepository.findAll().isEmpty()){
            throw new IllegalArgumentException("The exercises list is empty");
        }
        return exerciseRepository.findAll();
    }
}
