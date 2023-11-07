package com.mycompany.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.DTO.Person;
import com.mycompany.dataGroups.GroupCriterion;
import com.mycompany.loaders.FileDataLoader;
import com.mycompany.loaders.IDataLoader;
import com.mycompany.services.DataGroupStudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/*"})
public class Servlet extends HttpServlet {
    IDataLoader loader = new FileDataLoader("D:\\GitHubRepos\\Homework_RTK\\DataGroupsRostelecom\\students.csv");
    DataGroupStudentService dataGroupService = new DataGroupStudentService(loader);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        var mapper = new ObjectMapper();
        try (var output = resp.getWriter()) {
            //Параметр запроса
            int groupNumber = Integer.parseInt(req.getParameter("className"));

            if (dataGroupService.dataGroups[0] == null) {
                GroupCriterion<Person> classCriterion = Person::getGroup;
                dataGroupService.addPersonList(classCriterion, 0);
            }

            Person[] personList = dataGroupService.dataGroups[0].getPersons(groupNumber);

            // Создание списка объектов с необходимыми полями
            List<Map<String, Object>> personsInfo = new ArrayList<>();
            for (Person person : personList) {
                Map<String, Object> info = new HashMap<>();
                info.put("lastName", person.getSurname());
                info.put("firstName", person.getName());
                info.put("groupNumber", person.getGroup());
                info.put("averageGrade", person.getAvgGrade());

                personsInfo.add(info);
            }
            if (!personsInfo.isEmpty()) {
                String jsonResponse = mapper.writeValueAsString(personsInfo);
                resp.setContentType("application/json");
                output.write(jsonResponse);
                output.flush();
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // Параметры запроса
        try {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            int groupNumber = Integer.parseInt(req.getParameter("groupNumber"));
            String subject = req.getParameter("subject");
            int newGrade = Integer.parseInt(req.getParameter("newGrade"));


            if (dataGroupService.dataGroups[0] == null) {
                GroupCriterion<Person> classCriterion = Person::getGroup;
                dataGroupService.addPersonList(classCriterion, 0);
            }

            Person[] personList = dataGroupService.dataGroups[0].getPersons(groupNumber);
            boolean ismodified = false;
            for (Person person : personList) {
                if (person.getSurname().equals(lastName) && person.getName().equals(firstName) && person.getGroup() == groupNumber) {
                    person.getGradeList().replace(subject, newGrade);
                    float avgGrade = 0;
                    for (float grade : person.getGradeList().values()) {
                        avgGrade += grade;
                        ismodified = true;
                    }
                    person.setAvgGrade(avgGrade / person.getGradeList().size());
                    resp.setContentType("text/plain");
                    resp.getWriter().write("Оценка успешно обновлена для ученика " + firstName + " " + lastName + " в классе " + groupNumber + " по предмету " + subject);
                }
            }
            if (!ismodified) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
