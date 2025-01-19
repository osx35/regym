package com.example.webapi.services;

import com.example.data.model.User;
import com.example.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("Could not find user with id " + id);
        }
        return userRepository.findById(id).get();
    }

    public User updateUser(Long updatedUserId, User updatedUser) {
        User existingUser = getUserById(updatedUserId);

        if(!existingUser.getUserRole().equals(updatedUser.getUserRole())){
            existingUser.setUserRole(updatedUser.getUserRole());
        }

        if(!existingUser.getName().equals(updatedUser.getName())){
            existingUser.setName(updatedUser.getName());
        }

        if(!existingUser.getEmail().equals(updatedUser.getEmail())){
            existingUser.setEmail(updatedUser.getEmail());
        }

        if(!existingUser.getPhone().equals(updatedUser.getPhone())){
            existingUser.setPhone(updatedUser.getPhone());
        }

        if(!existingUser.getPass().equals(updatedUser.getPass())){
            existingUser.setPass(updatedUser.getPass());
        }

        if(!existingUser.getExerciseList().equals(updatedUser.getExerciseList())){
            existingUser.setExerciseList(updatedUser.getExerciseList());
        }

        if(existingUser.isActive() != updatedUser.isActive()){
            existingUser.setActive(updatedUser.isActive());
        }

        return userRepository.save(existingUser);
    }

    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("Could not find and delete user with id " + id);
        }
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        if(userRepository.findAll().isEmpty()){
            throw new IllegalArgumentException("The users list is empty");
        }
        return userRepository.findAll();
    }
}
