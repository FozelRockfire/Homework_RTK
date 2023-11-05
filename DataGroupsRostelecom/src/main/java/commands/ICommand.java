/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package commands;

import services.DataGroupStudentService;

import java.sql.SQLException;

/**
 *
 * @author Ilya Popov
 */
public interface ICommand {

    void execute() throws SQLException;
}
