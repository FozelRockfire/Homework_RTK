package commands.DBCommands;

import commands.ICommand;
import services.JDBCStudentService;

import java.sql.SQLException;

public class CommandDBOnlyFive implements ICommand {

    JDBCStudentService jdbcStudentService;

    public CommandDBOnlyFive(JDBCStudentService jdbcStudentService) {
        this.jdbcStudentService = jdbcStudentService;
    }

    @Override
    public void execute() throws SQLException {
        System.out.println("Отличники старше 14 лет:");
        System.out.println(jdbcStudentService.onlyFive());
    }
}
