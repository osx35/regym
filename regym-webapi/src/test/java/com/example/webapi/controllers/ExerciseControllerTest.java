package com.example.webapi.controllers;

import com.example.data.model.Exercise;
import com.example.webapi.services.ExerciseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static com.example.data.enums.TargetMuscle.CHEST;
import static com.example.data.enums.TargetMuscle.LEGS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExerciseControllerTest {

    @Mock
    private ExerciseService exerciseService;

    @InjectMocks
    private ExerciseController exerciseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create exercise - valid exercise")
    void createExercise_ValidExercise() {
        Exercise exercise = new Exercise(null, "Push-up", "Strength", LEGS, null);
        Exercise savedExercise = new Exercise(1L, "Push-up", "Strength", LEGS, null);
        when(exerciseService.createExercise(exercise)).thenReturn(savedExercise);

        ResponseEntity<Exercise> response = exerciseController.saveExercise(exercise);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedExercise, response.getBody());
    }

//    @Test
//    @DisplayName("Get exercise by ID - exercise exists")
//    void getExerciseById_ExerciseExists() {
//        Exercise exercise = new Exercise(1L, "Push-up", "Strength", LEGS, null);
//        when(exerciseService.getExerciseById(1L)).thenReturn(Optional.of(exercise));
//
//        ResponseEntity<Exercise> response = exerciseController.getExerciseById(1L);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(exercise, response.getBody());
//    }
//
//    @Test
//    @DisplayName("Get exercise by ID - exercise does not exist")
//    void getExerciseById_ExerciseDoesNotExist() {
//        when(exerciseService.getExerciseById(1L)).thenReturn(Optional.empty());
//
//        ResponseEntity<Exercise> response = exerciseController.getExerciseById(1L);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Update exercise - valid exercise")
//    void updateExercise_ValidExercise() {
//        Exercise updatedExercise = new Exercise(1L, "Pull-up", "Strength", LEGS, null);
//        when(exerciseService.updateExercise(1L, updatedExercise)).thenReturn(Optional.of(updatedExercise));
//
//        ResponseEntity<Exercise> response = exerciseController.updateExercise(1L, updatedExercise);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(updatedExercise, response.getBody());
//    }
//
//    @Test
//    @DisplayName("Update exercise - exercise does not exist")
//    void updateExercise_ExerciseDoesNotExist() {
//        Exercise updatedExercise = new Exercise(1L, "Pull-up", "Strength", LEGS, null);
//        when(exerciseService.updateExercise(1L, updatedExercise)).thenReturn(Optional.empty());
//
//        ResponseEntity<Exercise> response = exerciseController.updateExercise(1L, updatedExercise);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Delete exercise by ID - exercise exists")
//    void deleteExerciseById_ExerciseExists() {
//        when(exerciseService.deleteExerciseById(1L)).thenReturn(true);
//
//        ResponseEntity<Void> response = exerciseController.deleteExercise(1L);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Delete exercise by ID - exercise does not exist")
//    void deleteExerciseById_ExerciseDoesNotExist() {
//        when(exerciseService.deleteExerciseById(1L)).thenReturn(false);
//
//        ResponseEntity<Void> response = exerciseController.deleteExercise(1L);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }

    @Test
    @DisplayName("Get all exercises - exercises exist")
    void getAllExercises_ExercisesExist() {
        Exercise exercise1 = new Exercise(1L, "Push-up", "Strength", LEGS, List.of());
        Exercise exercise2 = new Exercise(2L, "Pull-up", "Weakness", CHEST, List.of());
        when(exerciseService.getAllExercises()).thenReturn(List.of(exercise1, exercise2));

        ResponseEntity<List<Exercise>> response = exerciseController.getAllExercises();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    @DisplayName("Get all exercises - no exercises exist")
    void getAllExercises_NoExercisesExist() {
        when(exerciseService.getAllExercises()).thenReturn(List.of());

        ResponseEntity<List<Exercise>> response = exerciseController.getAllExercises();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }
}