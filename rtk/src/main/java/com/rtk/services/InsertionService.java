package com.rtk.services;

import com.rtk.dto.GradeDTO;
import com.rtk.dto.CreateStudentWithGradesRequestDTO;
import com.rtk.entity.Grade;
import com.rtk.entity.GradeId;
import com.rtk.entity.Student;
import com.rtk.loaders.FileDataLoader;
import com.rtk.mapper.GradeMapper;
import com.rtk.mapper.StudentMapper;
import com.rtk.mapper.SubjectMapper;
import com.rtk.repository.GradeRepo;
import com.rtk.repository.StudentRepo;
import com.rtk.repository.SubjectRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsertionService {

    @Autowired
    private GradeRepo gradeRepo;
    @Autowired
    private SubjectRepo subjectRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private FileDataLoader dataLoader;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private GradeMapper gradeMapper;
    @Autowired
    private SubjectMapper subjectMapper;

    public void loadDBFromCSV() {
        List<CreateStudentWithGradesRequestDTO> studentList = dataLoader.loadData();

        List<Student> students = new ArrayList<>();
        List<Grade> grades = new ArrayList<>();

        List<String> addedSubjects= new ArrayList<>();

        for (int currentStudent = 0; currentStudent < studentList.size(); currentStudent++) {
            CreateStudentWithGradesRequestDTO studentWithGradesDTO = studentList.get(currentStudent);

            //Загрузка одного студента в таблицу
            Student student = studentMapper.toEntity(studentWithGradesDTO);
            students.add(student);

            //Обработка первой строки с предметами и загрузка всех пердметов в таблицу предметов
            List<GradeDTO> gradeDTOList = studentWithGradesDTO.getGrades();

            //Загрузка оценок одного студента таблицу
            for (GradeDTO gradeDTO : gradeDTOList) {
                //сохранение предметов в БД
                if (!addedSubjects.contains(gradeDTO.getSubjectName())){
                    addedSubjects.add(gradeDTO.getSubjectName());
                    subjectRepo.save(subjectMapper.toEntity(gradeDTO));
                }
                grades.add(addGrade(gradeDTO, student));
            }
            if (currentStudent % 2000 == 0 || currentStudent == studentList.size()-1) {
                studentRepo.saveAll(students);
                studentRepo.flush();
                students.clear();

                gradeRepo.saveAll(grades);
                gradeRepo.flush();
                grades.clear();
            }
        }
    }

    @Transactional
    public void addStudentWithGrades(CreateStudentWithGradesRequestDTO studentDTO) {

        Student student = studentMapper.toEntity(studentDTO);
        studentRepo.save(student);

        List<GradeDTO> gradeDTOs = studentDTO.getGrades();
        for (GradeDTO gradeDTO : gradeDTOs) {
            subjectRepo.save(subjectMapper.toEntity(gradeDTO));
            gradeRepo.save(addGrade(gradeDTO, student));
        }

    }


    private Grade addGrade(GradeDTO gradeDTO, Student student) {

        Grade grade = new Grade();
        grade.setId(new GradeId(student.getId(), gradeDTO.getSubjectName()));
        grade.setGrade(gradeDTO.getGrade());
        grade.setStudent(student);
        grade.setSubject(subjectRepo.findBySubjectName(gradeDTO.getSubjectName()));

        return grade;
    }
}
