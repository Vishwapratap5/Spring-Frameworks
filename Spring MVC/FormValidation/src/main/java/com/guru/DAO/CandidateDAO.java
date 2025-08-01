package com.guru.DAO;

import com.guru.Model.Candidate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class CandidateDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Candidate addCandidate(Candidate candidate) {
        String sql = "INSERT INTO candidate_info (name, email, phone, address) VALUES (?, ?, ?, ?)";
        PreparedStatementSetter pss = ps -> {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getEmail());
            ps.setString(3, candidate.getPhone());
            ps.setString(4, candidate.getAddress());
        };
        jdbcTemplate.update(sql, pss);
        return candidate;
    }

}
