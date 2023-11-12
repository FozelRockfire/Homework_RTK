package com.rtk.repository;

import com.rtk.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
    Student findByGroupNumber(int groupNumber);
}
