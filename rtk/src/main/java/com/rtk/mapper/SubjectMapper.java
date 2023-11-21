package com.rtk.mapper;

import com.rtk.dto.GradeDTO;
import com.rtk.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    @Mapping(source = "subjectName", target = "subjectName")
    Subject toEntity(GradeDTO dto);
}
