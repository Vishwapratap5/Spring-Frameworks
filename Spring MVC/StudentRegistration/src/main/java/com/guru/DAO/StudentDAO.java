package com.guru.DAO;

import com.guru.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Student getStudent(int id) {
        String sql = "select id, name, email, course as courseName from students where id=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Student.class),id);
    }

    public void addStudent(Student student) {
        String sql="insert into students (name,email,course) values (?,?,?);";
        jdbcTemplate.update(sql, student.getName(),student.getEmail(),student.getCourseName());
    }

    public void updateStudent(Student student) {
        String sql="update students set name=?,email=?,course=? where id=?";
    }
}
