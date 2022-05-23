package com.proj.babynames.repositories;

import java.util.List;

import com.proj.babynames.models.Vote;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

    List<Vote> findAll();

    List<Vote> findByUserIdIs(Long id);

    List<Vote> findByBabyIdIs(Long id);

    List<Vote> findByUserIdAndBabyId(Long userId, Long ventureId);

    void deleteByUserIdAndBabyId(Long userId, Long ventureId);
}
