package com.example.webapi.services;

import com.example.data.model.Gym;
import com.example.data.repositories.GymRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GymService {
    private final GymRepository gymRepository;

    public Gym createGym(Gym gym) {
        return gymRepository.save(gym);
    }

    public Gym getGymById(Long id) {
        if(!gymRepository.existsById(id)){
            throw new IllegalArgumentException("Could not find gym with id " + id);
        }
        return gymRepository.findById(id).get();
    }

    public Gym updateGym(Long updatedGymId, Gym updatedGym) {
        Gym existingGym = getGymById(updatedGymId);

        if(!existingGym.getName().equals(updatedGym.getName())){
            existingGym.setName(updatedGym.getName());
        }

        if(!existingGym.getAddress().equals(updatedGym.getAddress())){
            existingGym.setAddress(updatedGym.getAddress());
        }

        if(!existingGym.getPhone().equals(updatedGym.getPhone())){
            existingGym.setPhone(updatedGym.getPhone());
        }

        if(!existingGym.getOpeningHour().equals(updatedGym.getOpeningHour())){
            existingGym.setOpeningHour(updatedGym.getOpeningHour());
        }

        if(!existingGym.getClosingHour().equals(updatedGym.getClosingHour())){
            existingGym.setClosingHour(updatedGym.getClosingHour());
        }

        if(!existingGym.getEquipments().equals(updatedGym.getEquipments())){
            existingGym.setEquipments(updatedGym.getEquipments());
        }

        return gymRepository.save(updatedGym);
    }

    public void deleteGymById(Long id) {
        if(!gymRepository.existsById(id)){
            throw new IllegalArgumentException("Could not find and delete gym with id " + id);
        }
        gymRepository.deleteById(id);
    }

    public List<Gym> getAllGyms(){
        if(gymRepository.findAll().isEmpty()){
            throw new IllegalArgumentException("The gyms list is empty");
        }
        return gymRepository.findAll();
    }
}
