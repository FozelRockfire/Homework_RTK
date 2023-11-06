package com.mycompany.commands.DBCommands;

import com.mycompany.commands.ICommand;
import com.mycompany.services.JDBCStudentService;

import java.sql.SQLException;
import java.util.Scanner;

public class CommandDBGradeBySurname implements ICommand {
    JDBCStudentService jdbcStudentService;

    public CommandDBGradeBySurname(JDBCStudentService jdbcStudentService) {
        this.jdbcStudentService = jdbcStudentService;
    }

    @Override
    public void execute() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите фамилию ученика:");
        String surname = sc.nextLine();
        System.out.println("Список средних оценок учеников с фамилией " + surname + ":");
        System.out.println(jdbcStudentService.gradeBySurname(surname));
    }
}
