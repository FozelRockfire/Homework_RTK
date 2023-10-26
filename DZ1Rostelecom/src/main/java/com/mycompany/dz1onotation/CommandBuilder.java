/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dz1onotation;

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
            case "avgGrade": command = new avgGradeCommand(studentService); break;
            case "onlyFive": command = new onlyFiveCommand(studentService); break;
            case "sameSurname": command = new sameSurnameCommand(studentService); break;
            //case default: System.out.println("Неизвестаня комадна"); break;

        }
       command.execute();
    }
}
