package com.example.webapi.controllers;

import com.example.data.model.Equipment;
import com.example.webapi.services.EquipmentService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EquipmentControllerTest {

    @Mock
    private EquipmentService equipmentService;

    @InjectMocks
    private EquipmentController equipmentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create equipment - valid equipment")
    void createEquipment_ValidEquipment() {
        Equipment equipment = new Equipment(null, "Bench", "Strength", null, "Good", null);
        Equipment savedEquipment = new Equipment(1L, "Bench", "Strength", null, "Good", null);
        when(equipmentService.createEquipment(equipment)).thenReturn(savedEquipment);

        ResponseEntity<Equipment> response = equipmentController.saveEquipment(equipment);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedEquipment, response.getBody());
    }

//    @Test
//    @DisplayName("Get equipment by ID - equipment exists")
//    void getEquipmentById_EquipmentExists() {
//        Equipment equipment = new Equipment(1L, "Treadmill", "Cardio", List.of(gym), "Field tested", null);
//        when(equipmentService.getEquipmentById(1L)).thenReturn(Optional.of(equipment));
//
//        ResponseEntity<Equipment> response = equipmentController.getEquipmentById(1L);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(equipment, response.getBody());
//    }
//
//    @Test
//    @DisplayName("Get equipment by ID - equipment does not exist")
//    void getEquipmentById_EquipmentDoesNotExist() {
//        when(equipmentService.getEquipmentById(1L)).thenReturn(Optional.empty());
//
//        ResponseEntity<Equipment> response = equipmentController.getEquipmentById(1L);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Update equipment - valid equipment")
//    void updateEquipment_ValidEquipment() {
//        Equipment updatedEquipment = new Equipment(1L, "Treadmill", "Cardio", List.of(), "Field tested", null);
//        when(equipmentService.updateEquipment(1L, updatedEquipment)).thenReturn(Optional.of(updatedEquipment));
//
//        ResponseEntity<Equipment> response = equipmentController.updateEquipment(1L, updatedEquipment);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(updatedEquipment, response.getBody());
//    }
//
//    @Test
//    @DisplayName("Update equipment - equipment does not exist")
//    void updateEquipment_EquipmentDoesNotExist() {
//        Equipment updatedEquipment = new Equipment(1L, "Treadmill", "Cardio", List.of(), "Field tested", null);
//        when(equipmentService.updateEquipment(1L, updatedEquipment)).thenReturn(Optional.empty());
//
//        ResponseEntity<Equipment> response = equipmentController.updateEquipment(1L, updatedEquipment);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Delete equipment by ID - equipment exists")
//    void deleteEquipmentById_EquipmentExists() {
//        when(equipmentService.deleteEquipmentById(1L)).thenReturn(true);
//
//        ResponseEntity<Void> response = equipmentController.deleteEquipment(1L);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//    }
//
//    @Test
//    @DisplayName("Delete equipment by ID - equipment does not exist")
//    void deleteEquipmentById_EquipmentDoesNotExist() {
//        when(equipmentService.deleteEquipmentById(1L)).thenReturn(false);
//
//        ResponseEntity<Void> response = equipmentController.deleteEquipment(1L);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }

    @Test
    @DisplayName("Get all equipment - equipment exists")
    void getAllEquipment_EquipmentExists() {
        Equipment equipment1 = new Equipment(1L, "Bench", "Strength", List.of(), "Good", null);
        Equipment equipment2 = new Equipment(2L, "Treadmill", "Cardio", List.of(), "Field tested", null);
        when(equipmentService.getAllEquipments()).thenReturn(List.of(equipment1, equipment2));

        ResponseEntity<List<Equipment>> response = equipmentController.getAllEquipment();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    @DisplayName("Get all equipment - no equipment exists")
    void getAllEquipment_NoEquipmentExists() {
        when(equipmentService.getAllEquipments()).thenReturn(List.of());

        ResponseEntity<List<Equipment>> response = equipmentController.getAllEquipment();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }
}