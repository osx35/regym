package com.example.webapi.services;

import com.example.data.model.Equipment;
import com.example.data.model.Gym;
import com.example.data.repositories.GymRepository;
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

class GymServiceTest {

    @Mock
    private GymRepository gymRepository;

    @InjectMocks
    private GymService gymService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create gym - valid gym")
    void createGym_ValidGym() {
        Gym gym = new Gym(null, "Gym", "123 Street", "1234567890", 6.0, 22.0, null);
        Gym savedGym = new Gym(1L, "Gym", "123 Street", "1234567890", 6.0, 22.0, null);
        when(gymRepository.save(gym)).thenReturn(savedGym);

        Gym result = gymService.createGym(gym);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Gym", result.getName());
    }

    @Test
    @DisplayName("Get gym by ID - gym exists")
    void getGymById_GymExists() {
        Gym gym = new Gym(1L, "Gym", "123 Street", "1234567890", 6.0, 22.0, null);
        when(gymRepository.existsById(1L)).thenReturn(true);
        when(gymRepository.findById(1L)).thenReturn(Optional.of(gym));

        Gym result = gymService.getGymById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Gym", result.getName());
    }

    @Test
    @DisplayName("Get gym by ID - gym does not exist")
    void getGymById_GymDoesNotExist() {
        when(gymRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> gymService.getGymById(1L));
    }

    @Test
    @DisplayName("Update gym - valid gym")
    void updateGym_ValidGym() {
        Equipment existingEquipment = new Equipment(1L, "Bench", "Strength", null, "Good", null);

        Gym existingGym = new Gym(1L, "Gym", "123 Street", "1234567890", 6.0, 22.0, List.of());
        Gym updatedGym = new Gym(1L, "Reborn gym", "Targ drzewny", "0987654321", 8.0, 24.0, List.of(existingEquipment));

        when(gymRepository.existsById(1L)).thenReturn(true);
        when(gymRepository.findById(1L)).thenReturn(Optional.of(existingGym));
        when(gymRepository.save(any(Gym.class))).thenReturn(updatedGym);

        Gym result = gymService.updateGym(1L, updatedGym);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Reborn gym", result.getName());
        assertEquals("Targ drzewny", result.getAddress());
        assertEquals("0987654321", result.getPhone());
        assertEquals(8.0, result.getOpeningHour());
        assertEquals(24.0, result.getClosingHour());
        assertEquals(1, result.getEquipments().size());
    }

    @Test
    @DisplayName("Delete gym by ID - gym exists")
    void deleteGymById_GymExists() {
        when(gymRepository.existsById(1L)).thenReturn(true);

        gymService.deleteGymById(1L);

        verify(gymRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete gym by ID - gym does not exist")
    void deleteGymById_GymDoesNotExist() {
        when(gymRepository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> gymService.deleteGymById(1L));
    }

    @Test
    @DisplayName("Get all gyms - gyms exist")
    void getAllGyms_GymsExist() {
        Gym gym1 = new Gym(1L, "Gym", "123 Street", "1234567890", 6.0, 22.0, List.of());
        Gym gym2 = new Gym(1L, "Reborn gym", "Targ drzewny", "0987654321", 8.0, 24.0, List.of());
        when(gymRepository.findAll()).thenReturn(List.of(gym1, gym2));

        List<Gym> result = gymService.getAllGyms();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Gym", result.get(0).getName());
        assertEquals("Reborn gym", result.get(1).getName());
    }

    @Test
    @DisplayName("Get all gyms - no gyms exist")
    void getAllGyms_NoGymsExist() {
        when(gymRepository.findAll()).thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> gymService.getAllGyms());
    }
}