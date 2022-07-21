package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import static ua.edu.sumdu.j2se.say.tasks.model.Tasks.calendar;

public class CalendarView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        if (taskList.size() == 0) {
            System.out.println("The list of tasks is empty!");
            System.out.println("There are not tasks to display!");
        } else {
            System.out.println("Enter the time from which you want to display tasks!");
            System.out.println("Task time format example " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            LocalDateTime from;
            LocalDateTime to;
            while (true) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    from = LocalDateTime.parse(reader.readLine(), formatter);
                    if (from.isBefore(LocalDateTime.MIN)) {
                        System.out.println("`From time` is too far away in the past!!! Try again!!!");
                    } else if (from.isAfter(LocalDateTime.MAX)) {
                        System.out.println("`From time` is too far away in the future!!!");
                    } else {
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Time format must be "
                            + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                    System.out.println("Try again! Type start time and press ENTER!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Enter the time to which you want to display tasks!");
            System.out.println("Task time format example " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            while (true) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    to = LocalDateTime.parse(reader.readLine(), formatter);
                    if (to.isBefore(from)) {
                        System.out.println("`To time` should not be before `from time`!!! Try again!!!");
                    } else if (to.isAfter(LocalDateTime.MAX)) {
                        System.out.println("`To time` is too far away in the future!!! Try again!!!");
                    } else {
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Time format must be "
                            + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                    System.out.println("Try again! Type start time and press ENTER!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            SortedMap<LocalDateTime, Set<Task>> sortedMap = calendar(taskList, from, to);
            if (sortedMap.size() == 0) {
                System.out.println("There are no tasks that are performed in the specified period!");
            } else {
                System.out.println("Calendar from "
                        +from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                        + " to " + to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + ":");

                for (Map.Entry<LocalDateTime, Set<Task>> kv : sortedMap.entrySet()) {
                    System.out.print(kv.getKey().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                    System.out.println(kv.getValue());
                }
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
