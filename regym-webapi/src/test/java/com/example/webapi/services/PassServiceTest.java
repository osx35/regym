package com.example.webapi.services;

import com.example.data.enums.PassType;
import com.example.data.model.Pass;
import com.example.data.model.User;
import com.example.data.repositories.PassRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.data.enums.PassType.ANNUAL;
import static com.example.data.enums.PassType.MONTHLY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PassServiceTest {

    @Mock
    private PassRepository passRepository;

    @InjectMocks
    private PassService passService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Create pass - valid pass")
    void createPass_ValidPass() {
        Pass pass = new Pass(null, null, MONTHLY, null, null, 10.0);
        Pass savedPass = new Pass(1L, null, MONTHLY, null, null, 10.0);
        when(passRepository.save(pass)).thenReturn(savedPass);

        Pass result = passService.createPass(pass);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(MONTHLY, result.getPassType());
    }

    @Test
    @DisplayName("Get pass by ID - pass exists")
    void getPassById_PassExists() {
        Pass pass = new Pass(1L, null, MONTHLY, null, null, 10.0);
        when(passRepository.existsById(1L)).thenReturn(true);
        when(passRepository.findById(1L)).thenReturn(Optional.of(pass));

        Pass result = passService.getPassById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(MONTHLY, result.getPassType());
    }

    @Test
    @DisplayName("Get pass by ID - pass does not exist")
    void getPassById_PassDoesNotExist() {
        when(passRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> passService.getPassById(1L));
    }

    @Test
    @DisplayName("Update pass - valid pass")
    void updatePass_ValidPass() {
        User user1 = new User(1L, null, null, null, null, null, null, false);
        User user2 = new User(2L, null, null, null, null, null, null, false);
        LocalDateTime startDate1 = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime startDate2 = LocalDateTime.of(2024, 1, 1, 0, 0);

        LocalDateTime endDate1 = LocalDateTime.of(2023, 12, 31, 23, 59);
        LocalDateTime endDate2 = LocalDateTime.of(2024, 12, 31, 23, 59);
        Pass existingPass = new Pass(1L, user1, ANNUAL, startDate1, endDate1, 10.0);
        Pass updatedPass = new Pass(1L, user2, MONTHLY, startDate2, endDate2, 15.0);

        when(passRepository.existsById(1L)).thenReturn(true);
        when(passRepository.findById(1L)).thenReturn(Optional.of(existingPass));
        when(passRepository.save(any(Pass.class))).thenReturn(updatedPass);

        Pass result = passService.updatePass(1L, updatedPass);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(user2, result.getUser());
        assertEquals(MONTHLY, result.getPassType());
        assertEquals(startDate2, result.getStartDate());
        assertEquals(endDate2, result.getEndDate());
        assertEquals(15.0, result.getCost());
    }

    @Test
    @DisplayName("Delete pass by ID - pass exists")
    void deletePassById_PassExists() {
        when(passRepository.existsById(1L)).thenReturn(true);

        passService.deletePassById(1L);

        verify(passRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete pass by ID - pass does not exist")
    void deletePassById_PassDoesNotExist() {
        when(passRepository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> passService.deletePassById(1L));
    }

    @Test
    @DisplayName("Get all passes - passes exist")
    void getAllPasses_PassesExist() {
        Pass pass1 = new Pass(1L, null, MONTHLY, null, null, 10.0);
        Pass pass2 = new Pass(2L, null, MONTHLY, null, null, 10.0);
        when(passRepository.findAll()).thenReturn(List.of(pass1, pass2));

        List<Pass> result = passService.getAllPasses();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
    }

    @Test
    @DisplayName("Get all passes - no passes exist")
    void getAllPasses_NoPassesExist() {
        when(passRepository.findAll()).thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> passService.getAllPasses());
    }
}
