package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.NCTaskManager;
import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.model.Task;
import ua.edu.sumdu.j2se.say.tasks.model.TaskIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ChangeTaskView implements View {
    public AbstractTaskList taskList;

    public ChangeTaskView(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        if (taskList.size() == 0) {
            System.out.println("Tasks list is empty! There are not tasks to change!");
            return Controller.MAIN_MENU_ACTION;
        } else {
            System.out.println(taskList);
            System.out.println("Select task to change (type number) and press ENTER!");
            System.out.println("Or select 0 and press ENTER to go back!");
            int choice1;
            int index = 0;
            while (true) {
                try {
                    choice1 = Integer.parseInt(reader.readLine());
                    if (choice1 == 0
                            || (choice1 > 0 && (index = choice1 - 1) < taskList.size())) {
                        break;
                    } else {
                        System.out.println("There are not tasks with number " + choice1 + " in the list of tasks!");
                        System.out.println("Try again!");
                        System.out.println("Make your choice (type number) and press ENTER)!");
                        System.out.println("Number must be integer from 1 to " + taskList.size() + ".");
                        System.out.println("Or select 0 and press ENTER to go back!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Try again!");
                    System.out.println("Make your choice (type number) and press ENTER)!");
                    System.out.println("Number must be integer from 1 to " + taskList.size() + ".");
                    System.out.println("0 - Go back.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (choice1 == 0) {
                System.out.println("Going back!");
                return Controller.MAIN_MENU_ACTION;
            } else {
                System.out.println("Do you really want to change task #" + choice1 + " " + taskList.getTask(index) + " ?");
                System.out.println("Make your choice (type YES or NO) and press ENTER)!");
                String choice2;
                while (true) {
                    try {
                        choice2 = reader.readLine();
                        if (choice2.equals("YES") || choice2.equals("Yes") || choice2.equals("yes")
                                || choice2.equals("NO") || choice2.equals("No") || choice2.equals("no")) {
                            break;
                        } else {
                            System.out.println("Type YES or NO and press ENTER!");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (choice2.equals("YES") || choice2.equals("Yes") || choice2.equals("yes")) {
                    Task taskToChange = taskList.getTask(index);
                    if (taskToChange.isRepeated()) {
                        System.out.println("Task" + taskToChange);
                        System.out.println("Select what do you want to change:");
                        System.out.println("1. Title.");
                        System.out.println("2. Start time.");
                        System.out.println("3. End time.");
                        System.out.println("4. Repeat interval.");
                        System.out.println("5. Start time, end time and repeat interval.");
                        System.out.println("6. Make non-repeated.");
                        System.out.println("7. GO BACK!");
                        int choice3;
                        while (true) {
                            try {
                                choice3 = Integer.parseInt(reader.readLine());
                                if (choice3 == 1 || choice3 == 2 || choice3 == 3 || choice3 == 4 || choice3 == 5 || choice3 == 6 || choice3 == 7) {
                                    break;
                                } else {
                                    System.out.println("Try again!");
                                    System.out.println("Make your choice (type number and press ENTER)!");
                                    System.out.println("Task" + taskToChange);
                                    System.out.println("Select what do you want to change:");
                                    System.out.println("1. Title.");
                                    System.out.println("2. Start time.");
                                    System.out.println("3. End time.");
                                    System.out.println("4. Repeat interval.");
                                    System.out.println("5. Start time, end time and repeat interval.");
                                    System.out.println("6. Make non-repeated.");
                                    System.out.println("7. GO BACK!");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Try again!");
                                System.out.println("Make your choice (type number) and press ENTER)!");
                                System.out.println("You must type integer from 1 to 7.");
                                System.out.println("Task" + taskToChange);
                                System.out.println("Select what do you want to change:");
                                System.out.println("1. Title.");
                                System.out.println("2. Start time.");
                                System.out.println("3. End time.");
                                System.out.println("4. Repeat interval.");
                                System.out.println("5. Start time, end time and repeat interval.");
                                System.out.println("6. Make non-repeated.");
                                System.out.println("7. GO BACK!");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        switch (choice3) {
                            case 1 -> {
                                String newTitle = "";
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("Type new task's title and press ENTER!");
                                System.out.println("Or type 0 to go back!");
                                while (newTitle.isEmpty()) {
                                    try {
                                        newTitle = reader.readLine();
                                        if (newTitle.isEmpty()) {
                                            System.out.println("It does not make sense to create task without title!");
                                            System.out.println("Try again! Type task's title and press ENTER!");
                                            System.out.println("Or type 0 to go back!");
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (newTitle.equals("0")) {
                                    System.out.println("Going back!");
                                } else {
                                    taskToChange.setTitle(newTitle);
                                    TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                                    TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                                    System.out.println("Title changed successfully!");
                                }
                                return Controller.CHANGE_TASK_ACTION;
                            }

                            case 2 -> {
                                LocalDateTime newStart;
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("Type new start time and press ENTER!");
                                System.out.println("Time format must be " +
                                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                while (true) {
                                    try {
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                                        newStart = LocalDateTime.parse(reader.readLine(), formatter);
                                        if (newStart.isBefore(LocalDateTime.MIN)) {
                                            System.out.println("Start is too far away in the past!!! Try again!!!");
                                            System.out.println("Start must be after"
                                                    + LocalDateTime.MIN.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                            System.out.println("Type new start time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                        } else if (!newStart.plusSeconds(taskToChange.getRepeatInterval()).isBefore(taskToChange.getEndTime())) {
                                            System.out.println("With such a start time " + newStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                                                    + " , the task will never repeat until the end time "
                                                    + taskToChange.getEndTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
                                                    + "!!!");
                                            System.out.println("Try again!!! Type new start time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
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
                                taskToChange.setStart(newStart);
                                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                                TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                                System.out.println("Start time changed successfully!");
                                return Controller.CHANGE_TASK_ACTION;
                            }

                            case 3 -> {
                                LocalDateTime newEnd;
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("Type new end time and press ENTER!");
                                System.out.println("Time format must be " +
                                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                while (true) {
                                    try {
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                                        newEnd = LocalDateTime.parse(reader.readLine(), formatter);
                                        if (newEnd.isAfter(LocalDateTime.MAX)) {
                                            System.out.println("End is too far away in the future!!! Try again!!!");
                                            System.out.println("End time must be before "
                                                    + LocalDateTime.MAX.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "!");
                                            System.out.println("Type new end time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                        } else if (!newEnd.isAfter(taskToChange.getStartTime().plusSeconds(taskToChange.getRepeatInterval()))) {
                                            System.out.println("With such an end time " + newEnd.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + " , the task will never repeat!");
                                            System.out.println("End time must be after " + taskToChange.getStartTime().plusSeconds(taskToChange.getRepeatInterval())
                                                    + "in order for the task to be repeated at least once!");
                                            System.out.println("Type new end time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
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
                                taskToChange.setEnd(newEnd);
                                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                                TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                                System.out.println("End time changed successfully!");
                                return Controller.CHANGE_TASK_ACTION;
                            }

                            case 4 -> {
                                int newInterval;
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("Type new repeat interval in hours and press ENTER)!");
                                while (true) {
                                    try {
                                        newInterval = 3600 * Integer.parseInt(reader.readLine());
                                        if (newInterval == 0 || newInterval < 0) {
                                            System.out.println("Repeat interval must be positive integer!");
                                            System.out.println("Try again!");
                                            System.out.println("Type repeat interval in hours and press ENTER)!");
                                        } else if (!taskToChange.getStartTime().plusSeconds(newInterval).isBefore(taskToChange.getEndTime())) {
                                            System.out.println("Repeat interval " + newInterval / 3600
                                                    + " hours is too long!!! Task should not repeat before end time!!!");
                                            System.out.println("Try again! Type shorter repeat interval in hours and press ENTER!");
                                        } else {
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Try again!");
                                        System.out.println("Type repeat interval in hours and press ENTER!");
                                        System.out.println("Repeat interval must be positive integer!");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                taskToChange.setInterval(newInterval);
                                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                                TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                                System.out.println("Repeat interval changed successfully!");
                                return Controller.CHANGE_TASK_ACTION;
                            }

                            case 5 -> {
                                LocalDateTime newStart;
                                LocalDateTime newEnd;
                                int newInterval;
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("Type new start time and press ENTER!");
                                System.out.println("Time format must be " +
                                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                while (true) {
                                    try {
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                                        newStart = LocalDateTime.parse(reader.readLine(), formatter);
                                        if (newStart.isBefore(LocalDateTime.MIN)) {
                                            System.out.println("Start time is too far away in the past!!! Try again!!!");
                                            System.out.println("Start time must be after"
                                                    + LocalDateTime.MIN.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                            System.out.println("Type new start time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                        } else if (newStart.isAfter(LocalDateTime.MAX)) {
                                            System.out.println("Start time is too far away in the future!!! Try again!!!");
                                            System.out.println("Start time must be before "
                                                    + LocalDateTime.MAX.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "!");
                                            System.out.println("Type new end time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
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
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("New start time: " + newStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                System.out.println("Type new end time and press ENTER!");
                                System.out.println("Time format must be " +
                                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                while (true) {
                                    try {
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                                        newEnd = LocalDateTime.parse(reader.readLine(), formatter);
                                        if (newEnd.isAfter(LocalDateTime.MAX)) {
                                            System.out.println("End is too far away in the future!!! Try again!!!");
                                            System.out.println("End time must be before "
                                                    + LocalDateTime.MAX.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "!");
                                            System.out.println("Type new end time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                        } else if (!newEnd.isAfter(newStart)) {
                                            System.out.println("End time must be after start time " + newStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "!");
                                            System.out.println("Type new end time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
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
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("New start time: " + newStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                System.out.println("New end time: " + newEnd.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                System.out.println("Type new repeat interval in hours and press ENTER)!");
                                while (true) {
                                    try {
                                        newInterval = 3600 * Integer.parseInt(reader.readLine());
                                        if (newInterval == 0 || newInterval < 0) {
                                            System.out.println("Repeat interval must be positive integer!");
                                            System.out.println("Try again!");
                                            System.out.println("Type repeat interval in hours and press ENTER)!");
                                        } else if (!newStart.plusSeconds(newInterval).isBefore(newEnd)) {
                                            System.out.println("Repeat interval " + newInterval / 3600
                                                    + " hours is too long!!! Task should not repeat before end time!!!");
                                            System.out.println("Try again! Type shorter repeat interval in hours and press ENTER!");
                                        } else {
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Try again!");
                                        System.out.println("Type repeat interval in hours and press ENTER!");
                                        System.out.println("Repeat interval must be positive integer!");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                taskToChange.setTime(newStart, newEnd, newInterval);
                                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                                TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                                System.out.println("Start time, end time and repeat interval changed successfully!");
                                return Controller.CHANGE_TASK_ACTION;
                            }

                            case 6 -> {
                                taskToChange.setTime(taskToChange.getStartTime());
                                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                                TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                                System.out.println("Task # " + choice1 + " changed to non-repeated successfully!");
                                return Controller.CHANGE_TASK_ACTION;
                            }

                            case 7 -> {
                                System.out.println("Going back!");
                                return Controller.CHANGE_TASK_ACTION;
                            }
                        }

                    } else {
                        System.out.println("Task" + taskToChange);
                        System.out.println("Select what do you want to change:");
                        System.out.println("1. Title.");
                        System.out.println("2. Start time.");
                        System.out.println("3. Make repeated.");
                        System.out.println("4. GO BACK!");
                        int choice4;
                        while (true) {
                            try {
                                choice4 = Integer.parseInt(reader.readLine());
                                if (choice4 == 1 || choice4 == 2 || choice4 == 3 || choice4 == 4) {
                                    break;
                                } else {
                                    System.out.println("Try again!");
                                    System.out.println("Make your choice (type number and press ENTER)!");
                                    System.out.println("Task" + taskToChange);
                                    System.out.println("Select what do you want to change:");
                                    System.out.println("1. Title.");
                                    System.out.println("2. Start time.");
                                    System.out.println("3. Make repeated.");
                                    System.out.println("4. GO BACK!");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Try again!");
                                System.out.println("Make your choice (type number) and press ENTER)!");
                                System.out.println("You must type integer from 1 to 4.");
                                System.out.println("Task" + taskToChange);
                                System.out.println("Select what do you want to change:");
                                System.out.println("1. Title.");
                                System.out.println("2. Start time.");
                                System.out.println("3. Make repeated.");
                                System.out.println("4. GO BACK!");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        switch (choice4) {
                            case 1 -> {
                                String newTitle = "";
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("Type new task's title and press ENTER!");
                                System.out.println("Or type 0 to go back!");
                                while (newTitle.isEmpty()) {
                                    try {
                                        newTitle = reader.readLine();
                                        if (newTitle.isEmpty()) {
                                            System.out.println("It does not make sense to create task without title!");
                                            System.out.println("Try again! Type task's title and press ENTER!");
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (newTitle.equals("0")) {
                                    System.out.println("Going back!");
                                } else {
                                    taskToChange.setTitle(newTitle);
                                    TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                                    TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                                    System.out.println("Title changed successfully!");
                                }
                                return Controller.CHANGE_TASK_ACTION;
                            }
                            case 2 -> {
                                LocalDateTime newTime;
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("Type new start time and press ENTER!");
                                System.out.println("Time format must be " +
                                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                while (true) {
                                    try {
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                                        newTime = LocalDateTime.parse(reader.readLine(), formatter);
                                        if (newTime.isBefore(LocalDateTime.MIN)) {
                                            System.out.println("Start is too far away in the past!!! Try again!!!");
                                            System.out.println("Start must be after"
                                                    + LocalDateTime.MIN.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                            System.out.println("Type new start time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                        } else if (newTime.isAfter(LocalDateTime.MAX)) {
                                            System.out.println("Start is too far away in the future!!! Try again!!!");
                                            System.out.println("Start must be before"
                                                    + LocalDateTime.MAX.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                            System.out.println("Type new start time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
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
                                taskToChange.setTime(newTime);
                                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                                TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                                System.out.println("Start time changed successfully!");
                                return Controller.CHANGE_TASK_ACTION;
                            }
                            case 3 -> {
                                LocalDateTime newStart;
                                LocalDateTime newEnd;
                                int newInterval;
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("Type new start time and press ENTER!");
                                System.out.println("Time format must be " +
                                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                while (true) {
                                    try {
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                                        newStart = LocalDateTime.parse(reader.readLine(), formatter);
                                        if (newStart.isBefore(LocalDateTime.MIN)) {
                                            System.out.println("Start time is too far away in the past!!! Try again!!!");
                                            System.out.println("Start time must be after"
                                                    + LocalDateTime.MIN.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                            System.out.println("Type new start time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                        } else if (newStart.isAfter(LocalDateTime.MAX)) {
                                            System.out.println("Start time is too far away in the future!!! Try again!!!");
                                            System.out.println("Start time must be before "
                                                    + LocalDateTime.MAX.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "!");
                                            System.out.println("Type new end time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
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
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("New start time: " + newStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                System.out.println("Type new end time and press ENTER!");
                                System.out.println("Time format must be " +
                                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                while (true) {
                                    try {
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                                        newEnd = LocalDateTime.parse(reader.readLine(), formatter);
                                        if (newEnd.isAfter(LocalDateTime.MAX)) {
                                            System.out.println("End is too far away in the future!!! Try again!!!");
                                            System.out.println("End time must be before "
                                                    + LocalDateTime.MAX.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "!");
                                            System.out.println("Type new end time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                        } else if (!newEnd.isAfter(newStart)) {
                                            System.out.println("End time must be after start time " + newStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "!");
                                            System.out.println("Type new end time and press ENTER!");
                                            System.out.println("Time format must be " +
                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
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
                                System.out.println("Changing task #" + choice1 + " " + taskToChange + ".");
                                System.out.println("New start time: " + newStart.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                System.out.println("New end time: " + newEnd.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
                                System.out.println("Type new repeat interval in hours and press ENTER)!");
                                while (true) {
                                    try {
                                        newInterval = 3600 * Integer.parseInt(reader.readLine());
                                        if (newInterval == 0 || newInterval < 0) {
                                            System.out.println("Repeat interval must be positive integer!");
                                            System.out.println("Try again!");
                                            System.out.println("Type repeat interval in hours and press ENTER)!");
                                        } else if (!newStart.plusSeconds(newInterval).isBefore(newEnd)) {
                                            System.out.println("Repeat interval " + newInterval / 3600
                                                    + " hours is too long!!! Task should not repeat before end time!!!");
                                            System.out.println("Try again! Type shorter repeat interval in hours and press ENTER!");
                                        } else {
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Try again!");
                                        System.out.println("Type repeat interval in hours and press ENTER!");
                                        System.out.println("Repeat interval must be positive integer!");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                taskToChange.setTime(newStart, newEnd, newInterval);
                                TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                                TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                                System.out.println("Task # " + choice1 + " changed to repeated successfully!");
                                return Controller.CHANGE_TASK_ACTION;
                            }
                            case 4 -> {
                                System.out.println("Going back!");
                                return Controller.CHANGE_TASK_ACTION;
                            }
                        }
                    }
                } else {
                    System.out.println("Going back!");
                    return Controller.CHANGE_TASK_ACTION;
                }
            }
        }
        System.out.println("Unknown situation");
        return Controller.CHANGE_TASK_ACTION;
    }
}









