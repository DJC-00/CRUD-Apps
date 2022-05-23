package com.proj.babynames.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.proj.babynames.models.Vote;
import com.proj.babynames.repositories.VoteRepository;

import org.springframework.stereotype.Service;

@Service
public class VoteService {

	private final VoteRepository voteRepo;

	public VoteService(VoteRepository voteRepo) {
		this.voteRepo = voteRepo;
	}

	public List<Vote> allVotes() {
		return voteRepo.findAll();
	}

	public List<Vote> userVotes(Long id) {
		return voteRepo.findByUserIdIs(id);
	}

	public Integer babyVotes(Long id) {
		Integer voteCount = voteRepo.findByBabyIdIs(id).size();
		return voteCount;
	}

	public Vote createVote(Vote vote) {
		System.out.println("Createing Vote");
		return voteRepo.save(vote);
	}

	public Vote findVote(Long id) {

		Optional<Vote> optVote = voteRepo.findById(id);

		if (optVote.isPresent()) {
			return optVote.get();

		} else {

			return null;
		}
	}

	public Boolean voteCheck(Long userId, Long ventureId) {
		List<Vote> voteCheck = voteRepo.findByUserIdAndBabyId(userId, ventureId);
		Boolean bValue = voteCheck.size() == 0;
		return bValue;
	}

	@Transactional
	public void removeVote(Long userId, Long ventureId) {
		voteRepo.deleteByUserIdAndBabyId(userId, ventureId);
	}
}
