package com.rtk.services;

import com.rtk.dto.GradeDTO;
import com.rtk.dto.StudentWithGradesDTO;
import com.rtk.entity.Grade;
import com.rtk.entity.GradeId;
import com.rtk.entity.Student;
import com.rtk.entity.Subject;
import com.rtk.loaders.FileDataLoader;
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

    public void loadDBFromCSV() {
        List<StudentWithGradesDTO> studentList = dataLoader.loadData();

        List<Student> students = new ArrayList<>();
        List<Grade> grades = new ArrayList<>();

        for (int i = 0; i < studentList.size(); i++) {
            StudentWithGradesDTO studentWithGradesDTO = studentList.get(i);

            //Загрузка одного студента в таблицу
            Student student = addStudent(studentWithGradesDTO);
            students.add(student);

            //Обработка первой строки с предметами и загрузка всех пердметов в таблицу предметов
            List<GradeDTO> gradeDTOList = studentWithGradesDTO.getGrades();

            //Загрузка оценок одного студента таблицу
            for (GradeDTO gradeDTO : gradeDTOList) {
                if (i == 0){
                    subjectRepo.save(addSubject(gradeDTO));
                }
                grades.add(addGrade(gradeDTO, student));
            }
            if (i % 2000 == 0 || i == studentList.size()-1) {
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
    public Student addStudentWithGrades(StudentWithGradesDTO studentDTO) {

        Student student = addStudent(studentDTO);
        studentRepo.save(student);

        List<GradeDTO> gradeDTOs = studentDTO.getGrades();
        for (GradeDTO gradeDTO : gradeDTOs) {
            subjectRepo.save(addSubject(gradeDTO));
            gradeRepo.save(addGrade(gradeDTO, student));
        }

        return student;
    }

    private Student addStudent(StudentWithGradesDTO studentDTO){
        var student = new Student();
        student.setLastName(studentDTO.getLastName());
        student.setFirstName(studentDTO.getFirstName());
        student.setAge(studentDTO.getAge());
        student.setGroupNumber(studentDTO.getGroupNumber());

        return student;
    }

    private Subject addSubject(GradeDTO gradeDTO) {
        var subject = new Subject();
        subject.setSubjectName(gradeDTO.getSubjectName());

        return subject;
    }

    private Grade addGrade(GradeDTO gradeDTO, Student student) {

        var grade = new Grade();
        grade.setId(new GradeId(student.getId(), gradeDTO.getSubjectName()));
        grade.setGrade(gradeDTO.getGrade());
        grade.setStudent(student);
        grade.setSubject(subjectRepo.findBySubjectName(gradeDTO.getSubjectName()));

        return grade;
    }
}
