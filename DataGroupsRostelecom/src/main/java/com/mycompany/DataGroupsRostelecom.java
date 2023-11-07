/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany;

import com.mycompany.commands.CommandBuilder;
import com.mycompany.loaders.FileDataLoader;
import com.mycompany.loaders.IDataLoader;
import com.mycompany.services.DataGroupStudentService;

import java.util.Scanner;

/**
 * @author Ilya Popov
 */
public class DataGroupsRostelecom {

    public static void main(String[] args) {

        IDataLoader loader = new FileDataLoader("students.csv");
        DataGroupStudentService dataGroupService = new DataGroupStudentService(loader);
        CommandBuilder builder = new CommandBuilder(dataGroupService);

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
    }
}
