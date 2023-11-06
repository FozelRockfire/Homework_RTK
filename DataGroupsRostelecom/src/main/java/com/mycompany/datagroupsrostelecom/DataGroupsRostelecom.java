/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.datagroupsrostelecom;

import com.mycompany.commands.CommandBuilder;
import com.mycompany.loaders.FileDataLoader;
import com.mycompany.loaders.IDataLoader;
import com.mycompany.services.DataGroupStudentService;
import com.mycompany.services.JDBCStudentService;

import java.util.Scanner;

/**
 * @author Ilya Popov
 */
public class DataGroupsRostelecom {

    public static void main(String[] args) {
        try {
        IDataLoader loader = new FileDataLoader("students.csv");
        JDBCStudentService dbService;

            dbService = new JDBCStudentService(loader, args[0], args[1], args[2]);
            DataGroupStudentService dataGroupService = new DataGroupStudentService(loader);
            CommandBuilder builder = new CommandBuilder(dataGroupService, dbService);
        Scanner sc = new Scanner(System.in);
        String command;
        boolean isrunning = true;
        while (isrunning) {
            System.out.println("Введите команду");
            command = sc.nextLine();
            if (command.equals("exit")) {
                isrunning = false;
            } else {
                builder.createCommand(command);
            }
        }
        dbService.disconnect();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("не указаны аргументы для подключения к БД, введите команду следующим образом:\n" +
                               "java -jav DataGroupsRostelecom.jar URL название_таблицы пароль\n" +
                               "Пример: java -jav DataGroupsRostelecom.jar \"jdbc:postgresql://localhost:5432/postgres\" postgres postgres");
        }
    }
}
