package com.example.webapi.services;

import com.example.data.model.Equipment;
import com.example.data.repositories.EquipmentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment getEquipmentById(Long id) {
        if(!equipmentRepository.existsById(id)){
            throw new IllegalArgumentException("Could not find equipment with id " + id);
        }
        return equipmentRepository.findById(id).get();
    }

    public Equipment updateEquipment(Long updatedEquipmentId, Equipment updatedEquipment) {
        Equipment existingEquipment = getEquipmentById(updatedEquipmentId);

        if(!existingEquipment.getName().equals(updatedEquipment.getName())){
            existingEquipment.setName(updatedEquipment.getName());
        }

        if(!existingEquipment.getType().equals(updatedEquipment.getType())){
            existingEquipment.setType(updatedEquipment.getType());
        }

        if(!existingEquipment.getGyms().equals(updatedEquipment.getGyms())){
            existingEquipment.setGyms(updatedEquipment.getGyms());
        }

        if(!existingEquipment.getCondition().equals(updatedEquipment.getCondition())){
            existingEquipment.setCondition(updatedEquipment.getCondition());
        }

//        if(!existingEquipment.getExercise().equals(updatedEquipment.getExercise())){
//            existingEquipment.setExercise(updatedEquipment.getExercise());
//        }

        return equipmentRepository.save(existingEquipment);
    }

    public void deleteEquipmentById(Long id) {
        if(!equipmentRepository.existsById(id)){
            throw new IllegalArgumentException("Could not find and delete equipment with id " + id);
        }
        equipmentRepository.deleteById(id);
    }

    public List<Equipment> getAllEquipments() {
        if(equipmentRepository.findAll().isEmpty()){
            throw new IllegalArgumentException("The equipments list is empty");
        }
        return equipmentRepository.findAll();
    }
}
