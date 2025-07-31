package com.guru.DAO;

import com.guru.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;


@Repository
public class StudentDAO {
    JdbcTemplate jdbcTemplate;
    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }
    public Boolean addStudent(Student student) {
        String sql="Insert into StudentInfo(Name,age,mobilenum) values(?,?,?)";
        PreparedStatementSetter ps= ps1 -> {
            ps1.setString(1, student.getStudentName());
            ps1.setInt(2, student.getAge());
            ps1.setString(3, student.getMobileNum());
        };

       int rows= jdbcTemplate.update(sql,ps);
        if(rows>0){
            return true;
        }
        return false;
    }

    public Boolean deleteStudent(Student student) {
        String sql="Delete from StudentInfo where ID=?";
        PreparedStatementSetter ps= ps1 -> {
            ps1.setInt(1, student.getStudentID());
        };

        int rows= jdbcTemplate.update(sql,ps);
        if(rows>0){
            return true;
        }
        return false;
    }
}
