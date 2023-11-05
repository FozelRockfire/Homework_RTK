package services;

import DTO.Person;
import loaders.IDataLoader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCStudentService {

    private static String url;
    private static String username;
    private static String password;
    private final List<Person> personList;
    private static Connection connection;

    public JDBCStudentService(IDataLoader dataLoader, String url, String username, String password) {
        JDBCStudentService.url = url;
        JDBCStudentService.username = username;
        JDBCStudentService.password = password;
        personList = dataLoader.LoadData();
    }

    public void fillDB() throws SQLException {
        TransactionScript.getInstance().insertToDB(personList);
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("отключено");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<String> avgGrade() throws SQLException {
        return TransactionScript.getInstance().avgGrade();
    }

    public List<String> onlyFive() throws SQLException {
        return TransactionScript.getInstance().onlyFive();
    }

    public List<String> gradeBySurname(String surname) throws SQLException {
        return TransactionScript.getInstance().gradeBySurname(surname);
    }


    public static final class TransactionScript {

        private TransactionScript(String url, String username, String password) {
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private static final TransactionScript instance = new TransactionScript(url, username, password);
        public static TransactionScript getInstance() {
            return instance;
        }


        public void insertToDB (List<Person> personList) throws SQLException {
            connection.setAutoCommit(false);
            for (Person person : personList) {
                Savepoint savepoint;

                PreparedStatement addPerson = connection.prepareStatement("INSERT INTO persons (firstname, lastname, age, group_number) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                addPerson.setString(1, person.getName());
                addPerson.setString(2, person.getSurname());
                addPerson.setInt(3, person.getAge());
                addPerson.setInt(4, person.getGroup());
                addPerson.executeUpdate();
                ResultSet auto_person_pk = addPerson.getGeneratedKeys();

                PreparedStatement addSubject = connection.prepareStatement("INSERT INTO subjects (subject_name) VALUES (?)");
                String[] subjects = person.getSubjectList();
                for (String subject : subjects) {
                    savepoint = connection.setSavepoint("sp");
                    try {
                        addSubject.setString(1, subject);
                        addSubject.executeUpdate();
                    } catch (SQLException e) {
                        if (savepoint != null) {
                            connection.rollback(savepoint);
                        }
                    }
                }

                PreparedStatement addGrades = connection.prepareStatement("INSERT INTO grades (grade, person_id, subject_name) VALUES (?, ?, ?)");
                int[] grades = person.getGradeList();

                for (int i = 0; i < grades.length; i++) {
                    addGrades.setInt(1, grades[i]);
                    while (auto_person_pk.next()) {
                        int generatedId = auto_person_pk.getInt(1); // Получение сгенерированного значения первичного ключа
                        addGrades.setInt(2, generatedId);
                    }
                    addGrades.setString(3, person.getSubjectList()[i]);
                    addGrades.addBatch();
                }
                addGrades.executeBatch();
            }
            connection.commit();
        }

        public List<String> avgGrade() throws SQLException {
            PreparedStatement statement = connection.prepareStatement("SELECT s.subject_name, AVG(g.grade) AS average_grade\n" +
                                                                      "FROM grades g\n" +
                                                                      "JOIN persons p ON g.person_id = p.id\n" +
                                                                      "JOIN subjects s ON g.subject_name = s.subject_name\n" +
                                                                      "WHERE p.age IN (10, 11, 12)\n" +
                                                                      "GROUP BY s.subject_name;\n");

            ResultSet resultSet = statement.executeQuery();
            List<String> gradeList = new ArrayList<>();

            while (resultSet.next()) {
                String subjectName = resultSet.getString("subject_name");
                double averageGrade = resultSet.getDouble("average_grade");

                gradeList.add(subjectName + ", Средний балл: " + averageGrade);
            }
            return gradeList;
        }

        public List<String> onlyFive() throws SQLException {
            PreparedStatement statement = connection.prepareStatement("SELECT p.firstname, p.lastname, p.age\n" +
                                                                      "FROM persons p\n" +
                                                                      "JOIN grades g ON p.id = g.person_id\n" +
                                                                      "WHERE p.age > 14\n" +
                                                                      "GROUP BY p.id, p.firstname, p.lastname\n" +
                                                                      "HAVING AVG(g.grade) = 5;\n");

            ResultSet resultSet = statement.executeQuery();
            List<String> personList = new ArrayList<>();

            while (resultSet.next()) {
                String subjectName = resultSet.getString("firstname");
                String averageGrade = resultSet.getString("lastname");
                int age = resultSet.getInt("age");
                personList.add(subjectName + " " + averageGrade + " " + age + " лет");
            }
            return personList;
        }

        public List<String> gradeBySurname(String surname) throws SQLException {
            String sqlQuery = "SELECT p.firstname, p.lastname, p.group_number, AVG(g.grade) as average_grade\n" +
                              "FROM persons p\n" +
                              "JOIN grades g ON p.id = g.person_id\n" +
                              "WHERE p.lastname = ?\n" +
                              "GROUP BY p.id, p.firstname, p.lastname, p.group_number\n";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, surname);
            ResultSet resultSet = statement.executeQuery();
            List<String> personList = new ArrayList<>();

            while (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int groupNumber = resultSet.getInt("group_number");
                double averageGrade = resultSet.getDouble("average_grade");
                personList.add(firstname + " " + lastname + " Группа: " + groupNumber + " Средняя оценка: " + averageGrade);
            }
            return personList;
        }
    }
}
