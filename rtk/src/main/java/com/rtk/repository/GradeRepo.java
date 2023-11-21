package com.rtk.repository;

import com.rtk.dto.AverageGradeResponseDTO;
import com.rtk.entity.Grade;
import com.rtk.entity.GradeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepo extends JpaRepository<Grade, GradeId> {
    List<Grade> findByStudent_GroupNumber(int groupNumber);

    @Query("SELECT NEW com.rtk.dto.AverageGradeResponseDTO(g.student.id, g.student.firstName, g.student.lastName, AVG(g.grade)) " +
            "FROM Grade g " +
            "WHERE g.student.groupNumber = :groupNumber " +
            "GROUP BY g.student.id, g.student.firstName, g.student.lastName")
    List<AverageGradeResponseDTO> calculateAverageGradesByGroup(@Param("groupNumber") int groupNumber);

}
