package com.rtk.repository;


import com.rtk.entity.SubjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends CrudRepository<SubjectEntity, Integer> {
    SubjectEntity findBySubjectName(String subjectName);
}
