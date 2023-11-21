package com.rtk.mapper;

import com.rtk.dto.CreateStudentWithGradesRequestDTO;
import com.rtk.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "firstName", source = "dto.firstName")
    @Mapping(target = "lastName", source = "dto.lastName")
    @Mapping(target = "age", source = "dto.age")
    @Mapping(target = "groupNumber", source = "dto.groupNumber")
    @Mapping(target = "grades", ignore = true)
    Student toEntity(CreateStudentWithGradesRequestDTO dto);

}
