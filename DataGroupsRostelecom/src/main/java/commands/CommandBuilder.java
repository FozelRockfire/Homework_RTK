/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package commands;

import services.StudentService;

/**
 * @author Ilya Popov
 */
public class CommandBuilder {

    private final StudentService studentService;

    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
    }

    public void createCommand(String commandName) {

        ICommand command = null;
        switch (commandName) {
            case "help":
                command = StudentService::getHelp;
                break;
            case "avgGrade":
                command = StudentService::avgGrade;
                break;
            case "onlyFive":
                command = StudentService::onlyFive;
                break;
            case "sameSurname":
                command = StudentService::sameSurname;
                break;
            default:
                System.out.println("Неизвестная команда: " + commandName + " для получения списка команд используйте help");
        }
        if (command != null) {
            command.execute(studentService);
        }

    }
}
