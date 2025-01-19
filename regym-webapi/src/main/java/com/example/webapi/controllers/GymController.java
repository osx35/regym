package com.example.webapi.controllers;

import com.example.data.model.Gym;
import com.example.webapi.services.GymService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gym")
@RequiredArgsConstructor
@Tag(name = "Gym API", description = "Create, Read All, Read, Update, Delete")
public class GymController {
    private final GymService gymService;

    @PostMapping
    public ResponseEntity<Gym> saveGym(@RequestBody Gym gym) {
        return ResponseEntity.ok(gymService.createGym(gym));
    }

    @GetMapping
    public ResponseEntity<List<Gym>> getAllGyms() {
        return ResponseEntity.ok(gymService.getAllGyms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gym> getGymById(@PathVariable Long id) {
        return ResponseEntity.ok(gymService.getGymById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gym> updateGym(@PathVariable Long id, @RequestBody Gym gym) {
        return ResponseEntity.ok(gymService.updateGym(id, gym));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGym(@PathVariable Long id) {
        gymService.deleteGymById(id);
        return ResponseEntity.ok(id);
    }
}
