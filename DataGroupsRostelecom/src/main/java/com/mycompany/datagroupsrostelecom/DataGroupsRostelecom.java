/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.datagroupsrostelecom;

import java.util.Scanner;

/**
 *
 * @author Ilya Popov
 */
public class DataGroupsRostelecom {

    public static void main(String[] args) {

        IDataLoader loader = new FileDataLoader(args[0]);
        StudentService service = new StudentService(loader);
        CommandBuilder builder = new CommandBuilder(service);

        Scanner sc = new Scanner(System.in, "windows-1251");
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
