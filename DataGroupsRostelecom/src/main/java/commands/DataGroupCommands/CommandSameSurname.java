package commands.DataGroupCommands;

import DTO.Person;
import commands.ICommand;
import dataGroups.GroupCriterion;
import services.DataGroupStudentService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CommandSameSurname implements ICommand {

    DataGroupStudentService studentService;

    public CommandSameSurname(DataGroupStudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        GroupCriterion<Person> surnameCriterion = person -> (int) person.getSurname().charAt(0) - 1039;
        studentService.addPersonList(surnameCriterion);

        System.out.println("Задание 3: Поиск учеников по фамилии");
        ArrayList<String> personsBySurnameList = new ArrayList<>();
        // Используем группировку данных по первой букве фамилии, поскольку необходимо просматривать конкретную фамилию
        // Доступ к списку учеников с одинаковой первой буквой в фамилии, осуществляется за O(1)
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите фамилию для поиска: ");
        String surname = sc.nextLine();
        String firstLetter = surname.substring(0, 1).toUpperCase();
        try {
            for (Person person : studentService.dataGroup.getPersons(firstLetter)) {
                if (person.getSurname().equals(surname)) {
                    personsBySurnameList.add(person.getSurname() + " " + person.getName());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка: Неизвестный символ");
        }
        if (!personsBySurnameList.isEmpty()) {
            System.out.println("Список учеников с фамилией " + surname + " " + Arrays.toString(personsBySurnameList.toArray()));
        } else {
            System.out.println("Ученики с фамилией " + surname + " не найдены");
        }
    }
}
