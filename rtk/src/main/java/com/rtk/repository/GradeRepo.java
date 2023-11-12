package com.rtk.repository;

import com.rtk.entity.Grade;
import com.rtk.entity.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepo extends JpaRepository<Grade, GradeId> {
    List<Grade> findByStudent_GroupNumber(int groupNumber);
}
