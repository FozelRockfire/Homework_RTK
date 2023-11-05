package commands.DBCommands;

import commands.ICommand;
import services.JDBCStudentService;

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
    }
}
