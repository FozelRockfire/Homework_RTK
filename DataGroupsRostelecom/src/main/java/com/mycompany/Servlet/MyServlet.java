package com.mycompany.Servlet;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/*"})
public class MyServlet extends HttpServlet {

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Получить параметры из запроса
//        String className = request.getParameter("className");
//
//        // Здесь должна быть логика для получения средних оценок каждого ученика в указанном классе
//        // Это может включать в себя обращение к базе данных или другим источникам данных
//
//        // Пример ответа - JSON формата
//        Map<String, Double> averageGradesPerStudent = new HashMap<>();
//        // Логика для заполнения averageGradesPerStudent
//
//        response.setContentType("application/json");
//        response.getWriter().write(new Gson().toJson(averageGradesPerStudent));
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Получить параметры из запроса
//        String fullName = request.getParameter("fullName");
//        String className = request.getParameter("className");
//        String subject = request.getParameter("subject");
//        int newGrade = Integer.parseInt(request.getParameter("newGrade"));
//
//        // Здесь должна быть логика для обновления оценки конкретного ученика по предмету
//        // Это также может включать в себя обращение к базе данных или другим источникам данных
//
//        // Пример: Обновление оценки
//
//        // Ответ в случае успешного обновления
//        response.setContentType("text/plain");
//        response.getWriter().write("Оценка успешно обновлена для ученика " + fullName + " в классе " + className + " по предмету " + subject);
//    }
}
