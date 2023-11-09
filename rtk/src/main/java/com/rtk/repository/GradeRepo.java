package com.rtk.repository;

import com.rtk.entity.GradeEntity;
import com.rtk.entity.GradeId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepo extends CrudRepository<GradeEntity, GradeId> {
}
