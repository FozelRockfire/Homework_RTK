/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.datagroupsrostelecom;

import commands.CommandBuilder;
import loaders.FileDataLoader;
import loaders.IDataLoader;
import services.DataGroupStudentService;
import services.JDBCStudentService;

import java.util.Scanner;

/**
 * @author Ilya Popov
 */
public class DataGroupsRostelecom {

    public static void main(String[] args) {

        IDataLoader loader = new FileDataLoader("students.csv");
        JDBCStudentService dbService = new JDBCStudentService(loader, "jdbc:postgresql://localhost:5432/postgres", "postgres", "112");
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
    }
}
