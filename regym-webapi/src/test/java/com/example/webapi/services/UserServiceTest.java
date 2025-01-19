package com.example.webapi.services;

import com.example.data.enums.UserRole;
import com.example.data.model.Exercise;
import com.example.data.model.Pass;
import com.example.data.model.User;
import com.example.data.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.example.data.enums.TargetMuscle.LEGS;
import static com.example.data.enums.UserRole.ADMINISTRATOR;
import static com.example.data.enums.UserRole.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Find user by ID - user exists")
    void findUserById_UserExists() {
        User user = new User(1L, USER, "John Doe", "john.doe@example.com", "phone", null, null, true);
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    @DisplayName("Find user by ID - user does not exist")
    void findUserById_UserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(1L));
    }

    @Test
    @DisplayName("Create user - valid user")
    void createUser_ValidUser() {
        User user = new User(null, USER, "Jane Doe", "jane.doe@example.com", "phone", null, null, false);
        User savedUser = new User(1L, USER, "Jane Doe", "jane.doe@example.com", "phone", null, null, false);
        when(userRepository.save(user)).thenReturn(savedUser);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Jane Doe", result.getName());
        assertEquals("jane.doe@example.com", result.getEmail());
    }

    @Test
    @DisplayName("Update user - valid user")
    void updateUser_ValidUser() {
        Pass existingPass = new Pass(1L, null, null, null, null, 10.0);
        Pass updatedPass = new Pass(1L, null, null, null, null, 15.0);
        Exercise existingExercise = new Exercise(1L, "existingExercise", null, LEGS, null);
        Exercise updatedExercise = new Exercise(1L, "updatedExercise", null, LEGS, null);

        User existingUser = new User(1L, USER ,"John Doe", "john.doe@example.com", "phone", existingPass, List.of(existingExercise), true);
        User updatedUser = new User(1L, ADMINISTRATOR, "Jane Doe", "jane.doe@example.com", "newPhone", updatedPass, List.of(updatedExercise), false);

        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Jane Doe", result.getName());
        assertEquals("jane.doe@example.com", result.getEmail());
        assertEquals("newPhone", result.getPhone());
        assertEquals(ADMINISTRATOR, result.getUserRole());
        assertEquals(updatedPass, result.getPass());
        assertEquals(List.of(updatedExercise), result.getExerciseList());
        assertFalse(result.isActive());
    }

    @Test
    @DisplayName("Delete user by ID - user exists")
    void deleteUserById_UserExists() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deleteUserById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Delete user by ID - user does not exist")
    void deleteUserById_UserDoesNotExist() {
        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> userService.deleteUserById(1L));
    }

    @Test
    @DisplayName("Get all users - users exist")
    void getAllUsers_UsersExist() {
        User user1 = new User(1L, USER, "John Doe", "john.doe@example.com", "phone", null, null, true);
        User user2 = new User(2L, ADMINISTRATOR, "Jane Doe", "jane.doe@example.com", "phone", null, null, false);
        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
    }

    @Test
    @DisplayName("Get all users - no users exist")
    void getAllUsers_NoUsersExist() {
        when(userRepository.findAll()).thenReturn(List.of());

        assertThrows(IllegalArgumentException.class, () -> userService.getAllUsers());
    }

}