package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.NCTaskManager;
import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.model.Task;
import ua.edu.sumdu.j2se.say.tasks.model.TaskIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class RemoveTaskView implements View {
    public AbstractTaskList taskList;

    public RemoveTaskView(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public int printInfo(AbstractTaskList taskList) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        if (taskList.size() == 0) {
            System.out.println("Tasks list is empty! There are not tasks to remove!");
            return Controller.MAIN_MENU_ACTION;
        } else {
            System.out.println(taskList);
            System.out.println("Select task to remove (type number) and press ENTER!");
            System.out.println("Or select 0 and press ENTER to go back!");
            int choice1;
            int index = 0;
            while (true) {
                try {
                    choice1 = Integer.parseInt(reader.readLine());
                    if (choice1 == 0 || (index = choice1 - 1) < taskList.size()) {
                        break;
                    } else {
                        System.out.println("There is not task with number " + choice1 + " in the list of tasks!");
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
                return Controller.MAIN_MENU_ACTION;
            } else {
                System.out.println("Do you really want to remove task #" + choice1 + " " + taskList.getTask(index) + " from the list?");
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
                    Task taskToRemove = taskList.getTask(index);
                    taskList.remove(taskToRemove);
                    TaskIO.writeBinary(taskList, NCTaskManager.getTasksFile());
                    TaskIO.writeText(taskList, NCTaskManager.getTextFile());
                    System.out.println("Task # " + choice1 + " deleted from the list!");
                }
                return Controller.REMOVE_TASK_ACTION;
            }
        }
    }
}









