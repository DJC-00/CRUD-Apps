package com.proj.babynames.repositories;

import java.util.List;

import com.proj.babynames.models.Baby;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BabyRepository extends CrudRepository<Baby, Long> {

    List<Baby> findAll();

    // List<Baby> getBabyByBabyBabyName();

}
