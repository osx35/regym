package com.example.data.repositories;

import com.example.data.enums.UserRole;
import com.example.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUserRole(UserRole userRole);
    List<User> findAllByActiveIsTrue();
}
