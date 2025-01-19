package com.example.webapi.controllers;

import com.example.data.model.Pass;
import com.example.webapi.services.PassService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pass")
@RequiredArgsConstructor
@Tag(name = "Pass API", description = "Create, Read All, Read, Update, Delete")
public class PassController {
    private final PassService passService;

    @PostMapping
    public ResponseEntity<Pass> savePass(@RequestBody Pass pass) {
        return ResponseEntity.ok(passService.createPass(pass));
    }

    @GetMapping
    public ResponseEntity<List<Pass>> getAllPass() {
        return ResponseEntity.ok(passService.getAllPasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pass> getPassById(@PathVariable Long id) {
        return ResponseEntity.ok(passService.getPassById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pass> updatePass(@PathVariable Long id, @RequestBody Pass pass) {
        return ResponseEntity.ok(passService.updatePass(id, pass));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePass(@PathVariable Long id) {
        passService.deletePassById(id);
        return ResponseEntity.ok(id);
    }
}
