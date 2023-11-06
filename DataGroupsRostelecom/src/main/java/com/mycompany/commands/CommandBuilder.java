/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.commands;


import com.mycompany.commands.DataGroupCommands.CommandAvgGrade;
import com.mycompany.commands.DataGroupCommands.CommandOnlyFive;
import com.mycompany.commands.DataGroupCommands.CommandSameSurname;
import com.mycompany.services.DataGroupStudentService;


import java.sql.SQLException;

/**
 * @author Ilya Popov
 */
public class CommandBuilder {

    private final DataGroupStudentService dataGroupStudentService;

    public CommandBuilder(DataGroupStudentService studentService) {
        this.dataGroupStudentService = studentService;
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
            case "help":
                System.out.println("Доступные команды:\n" +
                                   "   1) avg_grade - Вычисление средней оценки в старших классах\n" +
                                   "   2) only_five - Поиск всех отличников, старше 14 лет\n" +
                                   "   3) same_surname - Поиск учеников по фамилии\n" +
                                   "   4) exit - остановка программы");
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
