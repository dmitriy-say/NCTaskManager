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
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(reader.readLine());
                if (choice == 1 || choice == 2 || choice == 3) {
                    break;
                } else {
                    System.out.println("Try again!");
                    System.out.println("Make your choice (type number and press ENTER)!");
                    System.out.println("1 - Add repeated task.");
                    System.out.println("2 - Add non-repeated task.");
                    System.out.println("3 - Go back.");
                }

            } catch (IOException | NumberFormatException e) {
                System.out.println("Try again!");
                System.out.println("Make your choice (type number and press ENTER)!");
                System.out.println("1 - Add repeated task.");
                System.out.println("2 - Add non-repeated task.");
                System.out.println("3 - Go back.");
            }
        }
        switch (choice) {
            case 1 -> {
                String title = "";
                LocalDateTime start;
                LocalDateTime end;
                int interval;
                System.out.println("Type task's title and press ENTER)!");
                while (title.isEmpty()) {
                    try {
                        title = reader.readLine();
                        if (title.isEmpty()) {
                            System.out.println("It does not make sense to create task without title!");
                            System.out.println("Try again! Type task's title and press ENTER!");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Type start time and press ENTER)!");
                System.out.println("Task time format example " +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                while (true) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        start = LocalDateTime.parse(reader.readLine(), formatter);
                        if(start.isBefore(LocalDateTime.MIN)) {
                            System.out.println("Start is too far in the past!!! Try again!!!");
                        } else if(start.isAfter(LocalDateTime.MAX)) {
                            System.out.println("Start is too far in the future!!!");
                        } else {
                            break;
                        }
                    } catch(DateTimeParseException e) {
                        System.out.println("Time format must be "
                                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                        System.out.println("Try again! Type start time and press ENTER!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Type end time and press ENTER)!");
                System.out.println("Task time format example " +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                while (true) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        end = LocalDateTime.parse(reader.readLine(), formatter);
                        if(!end.isAfter(start)) {
                            System.out.println("End must be after start!!! Try again!!!");
                        } else if(end.isAfter(LocalDateTime.MAX)) {
                            System.out.println("End is too far in the future!!!");
                        } else {
                            break;
                        }
                    } catch(DateTimeParseException e) {
                        System.out.println("Time format must be "
                                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                        System.out.println("Try again! Type start time and press ENTER!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("Type interval in hours and press ENTER)!");
                while (true) {
                    try {
                        interval = 3600 * Integer.parseInt(reader.readLine());
                        if (interval == 0 || interval < 0){
                            System.out.println("Interval must be positive integer!");
                            System.out.println("Try again!");
                            System.out.println("Type interval in hours and press ENTER)!");
                        } else if (!start.plusSeconds(interval).isBefore(end)) {
                            System.out.println("Interval " + interval/3600
                                    + " hours is too long!!! Task should not repeat!!!");
                            System.out.println("Try again! Type shorter interval in hours and press ENTER!");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Try again!");
                        System.out.println("Type interval in hours and press ENTER!");
                        System.out.println("Interval must be positive integer!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                taskList.add(new Task(title, start, end, interval));
                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                System.out.println("Task was successfully added to the tasks list.");
                return Controller.MAIN_MENU_ACTION;
            }

            case 2 -> {
                String title = "";
                LocalDateTime time;
                System.out.println("Type task's title and press ENTER!");
                while (title.isEmpty()) {
                    try {
                        title = reader.readLine();
                        if (title.isEmpty()) {
                            System.out.println("It does not make sense to create task without title!");
                            System.out.println("Try again! Type task's title and press ENTER!");
                        }
                    } catch (IOException e) {
                        System.out.println("Try again! Type task's title and press ENTER!");
                        e.printStackTrace();
                    }
                }
                System.out.println("Type start time and press ENTER!");
                System.out.println("Time format example "
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                while (true) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        time = LocalDateTime.parse(reader.readLine(), formatter);
                        if(time.isBefore(LocalDateTime.MIN)) {
                            System.out.println("Time is too far in the past!!! Try again!!!");
                        } else if(time.isAfter(LocalDateTime.MAX)) {
                            System.out.println("Time is too far in the future!!!");
                        } else {
                            break;
                        }
                    } catch(DateTimeParseException e) {
                        System.out.println("Time format must be "
                                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                        System.out.println("Try again! Type start time and press ENTER!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                taskList.add(new Task(title, time));
                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                System.out.println("Task was successfully added to the tasks list.");
                return Controller.MAIN_MENU_ACTION;
            }

            case 3 -> {
                return Controller.MAIN_MENU_ACTION;
            }

            default -> {
                System.out.println("Type 1, 2, or 3 and press Enter!");
                return Controller.ADD_TASK_ACTION;
            }
        }
    }
}
