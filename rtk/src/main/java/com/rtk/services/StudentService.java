package com.rtk.services;

import com.rtk.dto.GradeDTO;
import com.rtk.dto.StudentWithGradesDTO;
import com.rtk.entity.Grade;
import com.rtk.entity.GradeId;
import com.rtk.entity.Student;
import com.rtk.repository.GradeRepo;
import com.rtk.repository.StudentRepo;
import com.rtk.repository.SubjectRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private GradeRepo gradeRepo;
    @Autowired
    private SubjectRepo subjectRepo;

    @Transactional
    public Student addStudentWithGrades(StudentWithGradesDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setAge(studentDTO.getAge());
        student.setGroupNumber(studentDTO.getGroupNumber());
        studentRepo.save(student);

        List<GradeDTO> gradeDTOs = studentDTO.getGrades();
        for (GradeDTO gradeDTO : gradeDTOs) {

            Grade grade = new Grade();
            grade.setId(new GradeId(student.getId(), gradeDTO.getSubjectName()));
            grade.setStudent(student);
            grade.setSubject(subjectRepo.findBySubjectName(gradeDTO.getSubjectName()));
            grade.setGrade(gradeDTO.getGrade());
            gradeRepo.save(grade);
        }

        return student;
    }
}
