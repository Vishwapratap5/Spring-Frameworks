package com.guru.Service;

import com.guru.DAO.CandidateDAO;
import com.guru.Model.Candidate;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    private CandidateDAO candidateDAO;
    @Autowired
    public CandidateService(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO;
    }


    public  Candidate addCandidate(Candidate candidate) {
        return candidateDAO.addCandidate(candidate);
    }

    public Candidate getCandidateByID(int id) {
        return candidateDAO.getCandidateByID(id);
    }
}
