package com.proj.babynames.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.proj.babynames.models.Baby;
import com.proj.babynames.repositories.BabyRepository;

@Service
public class BabyService {
    // Injecting the repository
    private final BabyRepository babyRepo;

    public BabyService(BabyRepository babyRepo) {
        this.babyRepo = babyRepo;
    }

    // Find All Baby From Repo
    public List<Baby> allBabies() {
        return babyRepo.findAll();
    }

    // Create a Baby From Repo
    public Baby createBaby(Baby baby) {
        return babyRepo.save(baby);
    }

    // Find One Baby From Repo
    public Baby findBaby(Long id) {

        Optional<Baby> optBaby = babyRepo.findById(id);

        if (optBaby.isPresent()) {
            return optBaby.get();

        } else {

            return null;
        }
    }

    // Update a Baby From Repo
    public Baby updateBaby(Baby updatedBaby) {
        // Long babyID = updatedBaby.getId();
        Baby babyFromDB = findBaby(updatedBaby.getId());
        if (babyFromDB == null) {
            return null;
        }

        babyFromDB.setBabyName(updatedBaby.getBabyName());
        babyFromDB.setGender(updatedBaby.getGender());
        babyFromDB.setOrigin(updatedBaby.getOrigin());
        babyFromDB.setMeaning(updatedBaby.getMeaning());
        babyFromDB.setUser(updatedBaby.getUser());

        return babyRepo.save(babyFromDB);

        // Long id, String title, String description, Date dueDate, Date createdAt, Date
        // updatedAt
    }

    // Delete A Baby From Repo
    public void deleteBaby(Long id) {
        babyRepo.deleteById(id);
    }

    // public List<Baby> getBabyByName() {
    // return babyRepo.getBabyByBabyBabyName();
    // }
}