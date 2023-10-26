/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.datagroupsrostelecom;

import java.util.Arrays;

/**
 *
 * @author Ilya Popov
 */
public class DZ1Rostelecom {

    public static void main(String[] args) {

        FileDataLoader loader = new FileDataLoader("students.csv");
        StudentService service = new StudentService(loader);
        CommandBuilder builder = new CommandBuilder(service);

        String[] commandParams = Arrays.copyOfRange(args, 0, args.length);
        for (String commandParam : commandParams) {
            builder.createCommand(commandParam);
        }

    }
}
