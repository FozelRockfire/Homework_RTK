package com.mycompany.commands.DBCommands;

import com.mycompany.commands.ICommand;
import com.mycompany.services.JDBCStudentService;

import java.sql.SQLException;

public class CommandFillDB implements ICommand {

    JDBCStudentService jdbcStudentService;
    public CommandFillDB(JDBCStudentService jdbcStudentService) {
        this.jdbcStudentService = jdbcStudentService;
    }

    @Override
    public void execute() throws SQLException {
        System.out.println("Происходит загрузка данных в БД...");
        jdbcStudentService.fillDB();
        System.out.println("Загрузка данных в БД завершена");
    }
}
