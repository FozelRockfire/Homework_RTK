/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.commands;

import com.mycompany.commands.DBCommands.CommandDBAvgGrade;
import com.mycompany.commands.DBCommands.CommandDBGradeBySurname;
import com.mycompany.commands.DBCommands.CommandDBOnlyFive;
import com.mycompany.commands.DBCommands.CommandFillDB;
import com.mycompany.commands.DataGroupCommands.CommandAvgGrade;
import com.mycompany.commands.DataGroupCommands.CommandOnlyFive;
import com.mycompany.commands.DataGroupCommands.CommandSameSurname;
import com.mycompany.services.DataGroupStudentService;
import com.mycompany.services.JDBCStudentService;

import java.sql.SQLException;

/**
 * @author Ilya Popov
 */
public class CommandBuilder {

    private final DataGroupStudentService dataGroupStudentService;
    private final JDBCStudentService jdbcStudentService;

    public CommandBuilder(DataGroupStudentService studentService, JDBCStudentService jdbcStudentService) {
        this.dataGroupStudentService = studentService;
        this.jdbcStudentService = jdbcStudentService;
    }

    public void createCommand(String commandName) {

        ICommand command = null;
        switch (commandName) {
            case "avg_grade":
                command = new CommandAvgGrade(dataGroupStudentService);
                break;
            case "only_five":
                command = new CommandOnlyFive(dataGroupStudentService);
                break;
            case "same_surname":
                command = new CommandSameSurname(dataGroupStudentService);
                break;
            case "db_avg_grade":
                command = new CommandDBAvgGrade(jdbcStudentService);
                break;
            case "db_only_five":
                command = new CommandDBOnlyFive(jdbcStudentService);
                break;
            case "db_grade_by_surname":
                command = new CommandDBGradeBySurname(jdbcStudentService);
                break;
            case "db_fill":
                command = new CommandFillDB(jdbcStudentService);
                break;
            case "help":
                System.out.println("Доступные команды:\n" +
                                   "   1) avg_grade - Вычисление средней оценки в старших классах\n" +
                                   "   2) only_five - Поиск всех отличников, старше 14 лет\n" +
                                   "   3) same_surname - Поиск учеников по фамилии\n" +
                                   "   4) db_avg_grade - Вычисление средней оценки в старших классах из данных в БД\n" +
                                   "   5) db_only_five - Поиск всех отличников, старше 14 лет из данных в БД\n" +
                                   "   6) db_grade_by_surname - Поиск средней оценки по фамилии из данных в БД\n" +
                                   "   7) db_fill - Заполняет БД из файла students.csv\n" +
                                   "   7) exit - остановка программы");
                break;
            default:
                System.out.println("Неизвестная команда: " + commandName + " для получения списка команд используйте help");
        }
        if (command != null) {
            try {
                command.execute();
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
