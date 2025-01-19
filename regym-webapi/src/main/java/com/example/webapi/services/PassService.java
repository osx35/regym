package com.example.webapi.services;

import com.example.data.model.Pass;
import com.example.data.repositories.PassRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PassService {
    private final PassRepository passRepository;

    public Pass createPass(Pass pass) {
        return passRepository.save(pass);
    }

    public Pass getPassById(Long id) {
        if(!passRepository.existsById(id)){
            throw new IllegalArgumentException("Could not find pass with id " + id);
        }
        return passRepository.findById(id).get();
    }

    public Pass updatePass(Long updatedPassId, Pass updatedPass) {
        Pass existingPass = getPassById(updatedPassId);

        if(!existingPass.getUser().equals(updatedPass.getUser())){
            existingPass.setUser(updatedPass.getUser());
        }

        if(!existingPass.getPassType().equals(updatedPass.getPassType())){
            existingPass.setPassType(updatedPass.getPassType());
        }

        if(!existingPass.getStartDate().equals(updatedPass.getStartDate())){
            existingPass.setStartDate(updatedPass.getStartDate());
        }

        if(!existingPass.getEndDate().equals(updatedPass.getEndDate())){
            existingPass.setEndDate(updatedPass.getEndDate());
        }

        if(!existingPass.getCost().equals(updatedPass.getCost())){
            existingPass.setCost(updatedPass.getCost());
        }

        return passRepository.save(existingPass);
    }

    public void deletePassById(Long id) {
        if(!passRepository.existsById(id)){
            throw new IllegalArgumentException("Could not find and delete pass with id " + id);
        }
        passRepository.deleteById(id);
    }

    public List<Pass> getAllPasses(){
        if(passRepository.findAll().isEmpty()){
            throw new IllegalArgumentException("The passes list is empty");
        }
        return passRepository.findAll();
    }
}
