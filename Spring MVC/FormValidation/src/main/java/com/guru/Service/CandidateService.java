package com.guru.Service;

import com.guru.DAO.CandidateDAO;
import com.guru.Model.Candidate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    private CandidateDAO candidateDAO;
    @Autowired
    public CandidateService(CandidateDAO candidateDAO){
        this.candidateDAO=candidateDAO;
    }
    public Candidate addCandidate(@Valid Candidate candidate) {
        return candidateDAO.addCandidate(candidate);

    }
}
