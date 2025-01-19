package com.example.webapi.services;

import com.example.data.model.Equipment;
import com.example.data.model.Exercise;
import com.example.data.model.Gym;
import com.example.data.repositories.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @InjectMocks
    private EquipmentService equipmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create equipment - valid equipment")
    void createEquipment_ValidEquipment() {
        Equipment equipment = new Equipment(null, "Bench", "Strength", null, "Good", null);
        Equipment savedEquipment = new Equipment(1L, "Bench", "Strength", null, "Good", null);
        when(equipmentRepository.save(equipment)).thenReturn(savedEquipment);

        Equipment result = equipmentService.createEquipment(equipment);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Bench", result.getName());
    }

    @Test
    @DisplayName("Get equipment by ID - equipment exists")
    void getEquipmentById_EquipmentExists() {
        Equipment equipment = new Equipment(1L, "Bench", "Strength", null, "Good", null);
        when(equipmentRepository.existsById(1L)).thenReturn(true);
        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(equipment));

        Equipment result = equipmentService.getEquipmentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Bench", result.getName());
    }

    @Test
    @DisplayName("Get equipment by ID - equipment does not exist")
    void getEquipmentById_EquipmentDoesNotExist() {
        when(equipmentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> equipmentService.getEquipmentById(1L));
    }

    @Test
    @DisplayName("Update equipment - valid equipment")
    void updateEquipment_ValidEquipment() {
        Gym gym = new Gym(1L, "Gym", "123 Street", "1234567890", 6.0, 22.0, null);
        Exercise exercise = new Exercise(1L, "Bench Press", "Strength", null, null);

        Equipment existingEquipment = new Equipment(1L, "Bench", "Strength", List.of(), "Good", null);
        Equipment updatedEquipment = new Equipment(1L, "Treadmill", "Cardio", List.of(gym), "Field tested", exercise);

        when(equipmentRepository.existsById(1L)).thenReturn(true);
        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(existingEquipment));
        when(equipmentRepository.save(any(Equipment.class))).thenReturn(updatedEquipment);

        Equipment result = equipmentService.updateEquipment(1L, updatedEquipment);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Treadmill", result.getName());
        assertEquals("Cardio", result.getType());
        assertEquals("Field tested", result.getCondition());
        assertEquals("Gym", result.getGyms().get(0).getName());
        assertEquals("Bench Press", result.getExercise().getName());
    }

    @Test
    @DisplayName("Delete equipment by ID - equipment exists")
    void deleteEquipmentById_EquipmentExists() {
        when(equipmentRepository.existsById(1L)).thenReturn(true);

        equipmentService.deleteEquipmentById(1L);

        verify(equipmentRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete equipment by ID - equipment does not exist")
    void deleteEquipmentById_EquipmentDoesNotExist() {
        when(equipmentRepository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> equipmentService.deleteEquipmentById(1L));
    }

    @Test
    @DisplayName("Get all equipment - equipment exists")
    void getAllEquipment_EquipmentExists() {
        Equipment equipment1 = new Equipment(1L, "Bench", "Strength", List.of(), "Good", null);
        Equipment equipment2 = new Equipment(2L, "Treadmill", "Cardio", List.of(), "Field tested", null);
        when(equipmentRepository.findAll()).thenReturn(List.of(equipment1, equipment2));

        List<Equipment> result = equipmentService.getAllEquipments();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Treadmill", result.get(1).getName());
        assertEquals("Bench", result.get(0).getName());
    }

    @Test
    @DisplayName("Get all equipment - no equipment exists")
    void getAllEquipment_NoEquipmentExists() {
        when(equipmentRepository.findAll()).thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> equipmentService.getAllEquipments());
    }
}