package com.guru.DAO;

import com.guru.Model.Candidate;
import com.guru.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class CandidateDAO {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public CandidateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Candidate addCandidate(Candidate candidate) {
        String sql = "insert into Candidate_info(name,email,phone,Address) values (?,?,?,?)";
        PreparedStatementSetter pss = ps -> {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getEmail());
            ps.setString(3,candidate.getMobile());
            ps.setString(4,candidate.getAddress());
        };
        jdbcTemplate.update(sql,pss);
        return candidate;
    }

    public Candidate getCandidateByID(int id) {
        String sql = "select * from Candidate_info where id=?";

       return jdbcTemplate.queryForObject(sql,(rs,rownum)->{
            return Candidate.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .mobile(rs.getString("phone"))
                    .address(rs.getString("Address"))
                    .build();
        },id);

    }

}
