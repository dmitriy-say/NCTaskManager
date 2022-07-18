package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.NCTaskManager;
import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.model.Task;
import ua.edu.sumdu.j2se.say.tasks.model.TaskIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddTaskView implements View {


    public AbstractTaskList taskList;

    public AddTaskView(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Make your choice (type number and press ENTER)!");
        System.out.println("1 - Add repeated task.");
        System.out.println("2 - Add non-repeated task.");
        System.out.println("3 - Go back.");
        int choice = 0;
        try{
            choice = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.out.println("OIException caught!!!!");
            e.printStackTrace();
        }
        switch (choice) {
            case 2 -> {
                String title = null;
                LocalDateTime time = null;

                System.out.println("Type task's title and press ENTER!");
                try {
                    title = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Type start time and press ENTER!");
                System.out.println("Task time format example " +
                        LocalDateTime.now()); //.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                try {
                    time = LocalDateTime.parse(reader.readLine());
                } catch (IOException | DateTimeParseException e) {
                    e.printStackTrace();
                }
                taskList.add(new Task(title, time));
                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                System.out.println("Task was successfully added to the tasks list.");
                return Controller.MAIN_MENU_ACTION;
            }
            case 1 -> {
                String title = null;
                LocalDateTime start = null;
                LocalDateTime end = null;
                int interval = 0;

                System.out.println("Type task's title and press ENTER)!");
                try {
                    title = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Type start time and press ENTER)!");
                System.out.println("Task time format example " +
                        LocalDateTime.now());
                try {
                    start = LocalDateTime.parse(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Type end time and press ENTER)!");
                System.out.println("Task time format example " +
                        LocalDateTime.now());
                try {
                    end = LocalDateTime.parse(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Type interval and press ENTER)!");
                try {
                    interval = Integer.parseInt(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                taskList.add(new Task(title, start, end, interval));
                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                System.out.println("Task was successfully added to the tasks list.");
                return Controller.MAIN_MENU_ACTION;
            }
            default -> {
                System.out.println("Type 1, 2, or 3 and press Enter!");
                return Controller.ADD_TASK_ACTION;
            }
        }
    }
}
