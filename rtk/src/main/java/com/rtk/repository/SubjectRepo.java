package com.rtk.repository;


import com.rtk.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Integer> {
    Subject findBySubjectName(String subjectName);
}
