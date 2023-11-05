package commands.DBCommands;

import commands.ICommand;
import services.JDBCStudentService;

import java.sql.SQLException;

public class CommandDBAvgGrade implements ICommand {
    JDBCStudentService jdbcStudentService;

    public CommandDBAvgGrade(JDBCStudentService jdbcStudentService) {
        this.jdbcStudentService = jdbcStudentService;
    }

    @Override
    public void execute() throws SQLException {
        System.out.println("Список средних баллов по предметам в старших классах (10, 11, 12)");
        System.out.println(jdbcStudentService.avgGrade());
    }
}
