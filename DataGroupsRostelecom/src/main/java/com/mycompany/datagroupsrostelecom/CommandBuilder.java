/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datagroupsrostelecom;

/**
 *
 * @author Ilya Popov
 */
public class CommandBuilder {

    private final StudentService studentService;

    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
    }

    public void createCommand(String commandName) {

        Command command = null;
        switch (commandName) {
            case "avgGrade":
                command = new avgGradeCommand(studentService);
                break;
            case "onlyFive":
                command = new onlyFiveCommand(studentService);
                break;
            case "sameSurname":
                command = new sameSurnameCommand(studentService);
                break;
        }
        command.execute();
    }
}
