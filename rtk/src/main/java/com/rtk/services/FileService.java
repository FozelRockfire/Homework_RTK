package com.rtk.services;

import com.rtk.entity.Grade;
import com.rtk.entity.GradeId;
import com.rtk.entity.Student;
import com.rtk.entity.Subject;
import com.rtk.loaders.FileDataLoader;
import com.rtk.repository.GradeRepo;
import com.rtk.repository.StudentRepo;
import com.rtk.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private GradeRepo gradeRepo;
    @Autowired
    private SubjectRepo subjectRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private FileDataLoader dataLoader;

    public void loadDBFromCSV() {
        var stringList = dataLoader.LoadData();

        Student student;
        Subject subject;
        Grade grade;

        List<Student> students = new ArrayList<>();
        List<Grade> grades = new ArrayList<>();

        String[] params;
        String[] firstLineParams = null;

        double startTime = System.currentTimeMillis();

        for (int i = 0; i < stringList.size(); i++) {


            //Обработка первой строки с предметами и загрузка всех пердметов в таблицу предметов
            if (i == 0) {
                firstLineParams = stringList.get(i);
                for (int j = 4; j < firstLineParams.length; j++) {
                    subject = new Subject();
                    subject.setSubjectName(firstLineParams[j]);
                    subjectRepo.save(subject);
                }
            } else {
                params = stringList.get(i);

                //Загрузка одного студента в таблицу
                student = new Student();
                student.setLastName(params[0]);
                student.setFirstName(params[1]);
                student.setAge(Integer.parseInt(params[2]));
                student.setGroupNumber(Integer.parseInt(params[3]));
                students.add(student);

                //Загрузка оценок одного студента таблицу
                for (int j = 4; j < params.length; j++) {
                    grade = new Grade();
                    grade.setId(new GradeId(student.getId(), firstLineParams[j]));
                    grade.setGrade(Integer.parseInt(params[j]));
                    grade.setStudent(student);
                    grade.setSubject(subjectRepo.findBySubjectName(firstLineParams[j]));
                    grades.add(grade);
                }
            }
            if (i % 2000 == 0) {
                studentRepo.saveAll(students);
                studentRepo.flush();
                students.clear();

                gradeRepo.saveAll(grades);
                gradeRepo.flush();
                grades.clear();

                System.out.println(i + " " + ((System.currentTimeMillis() - startTime) / (1000)));
            }
        }

        double endTime = System.currentTimeMillis();
        double duration = endTime - startTime;

        System.out.println("Время выполнения: " + duration / (1000) + " секунд");
    }
}
