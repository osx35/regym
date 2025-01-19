package com.example.webapi.services;

import com.example.data.model.Equipment;
import com.example.data.model.Exercise;
import com.example.data.repositories.ExerciseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.example.data.enums.TargetMuscle.CHEST;
import static com.example.data.enums.TargetMuscle.LEGS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExerciseServiceTest {

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseService exerciseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create exercise - valid exercise")
    void createExercise_ValidExercise() {
        Exercise exercise = new Exercise(null, "Push-up", "Strength", LEGS, null);
        Exercise savedExercise = new Exercise(1L, "Push-up", "Strength", LEGS, null);
        when(exerciseRepository.save(exercise)).thenReturn(savedExercise);

        Exercise result = exerciseService.createExercise(exercise);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Push-up", result.getName());
    }

    @Test
    @DisplayName("Get exercise by ID - exercise exists")
    void getExerciseById_ExerciseExists() {
        Exercise exercise = new Exercise(1L, "Push-up", "Strength", LEGS, null);
        when(exerciseRepository.existsById(1L)).thenReturn(true);
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));

        Exercise result = exerciseService.getExerciseById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Push-up", result.getName());
    }

    @Test
    @DisplayName("Get exercise by ID - exercise does not exist")
    void getExerciseById_ExerciseDoesNotExist() {
        when(exerciseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> exerciseService.getExerciseById(1L));
    }

    @Test
    @DisplayName("Update exercise - valid exercise")
    void updateExercise_ValidExercise() {
        Equipment equipment1 = new Equipment(1L, "Bench", "Strength", null, "Good", null);
        Exercise existingExercise = new Exercise(1L, "Push-up", "Strength", LEGS, List.of());
        Exercise updatedExercise = new Exercise(2L, "Pull-up", "Weakness", CHEST, List.of(equipment1));

        when(exerciseRepository.existsById(1L)).thenReturn(true);
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(existingExercise));
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(updatedExercise);

        Exercise result = exerciseService.updateExercise(1L, updatedExercise);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Pull-up", result.getName());
        assertEquals("Weakness", result.getDescription());
        assertEquals(CHEST, result.getTargetMuscle());
        assertEquals(1, result.getEquipments().size());
    }

    @Test
    @DisplayName("Delete exercise by ID - exercise exists")
    void deleteExerciseById_ExerciseExists() {
        when(exerciseRepository.existsById(1L)).thenReturn(true);

        exerciseService.deleteExerciseById(1L);

        verify(exerciseRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete exercise by ID - exercise does not exist")
    void deleteExerciseById_ExerciseDoesNotExist() {
        when(exerciseRepository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> exerciseService.deleteExerciseById(1L));
    }

    @Test
    @DisplayName("Get all exercises - exercises exist")
    void getAllExercises_ExercisesExist() {
        Exercise exercise1 = new Exercise(1L, "Push-up", "Strength", LEGS, List.of());
        Exercise exercise2 = new Exercise(2L, "Pull-up", "Weakness", CHEST, List.of());
        when(exerciseRepository.findAll()).thenReturn(List.of(exercise1, exercise2));

        List<Exercise> result = exerciseService.getAllExercises();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Push-up", result.get(0).getName());
        assertEquals("Pull-up", result.get(1).getName());
    }

    @Test
    @DisplayName("Get all exercises - no exercises exist")
    void getAllExercises_NoExercisesExist() {
        when(exerciseRepository.findAll()).thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> exerciseService.getAllExercises());
    }
}