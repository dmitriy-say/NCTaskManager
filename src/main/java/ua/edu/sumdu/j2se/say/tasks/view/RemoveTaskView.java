package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        } else {
            System.out.println(taskList);
            System.out.println("Select task to remove (type number) and press ENTER!");
            System.out.println("Select 0 and press ENTER to go back!");
            int choice;
            int index;
            while (true) {
                try {
                    choice = Integer.parseInt(reader.readLine()) ;
                    index = choice - 1;
                    if (choice == 0) {
                        break;
                        return Controller.MAIN_MENU_ACTION;
                    } else if ((index = choice - 1) < 0 || index > taskList.size() - 1) {
                        System.out.println("The number is outside the list of tasks!");
                    } else {
                        break;
                    }
                } catch (IOException | NumberFormatException e) {
                    System.out.println("Try again!");
                    System.out.println("Make your choice (type number) and press ENTER)!");
                    System.out.println("1 - Add repeated task.");
                    System.out.println("2 - Add non-repeated task.");
                    System.out.println("3 - Go back.");
                }
            }
            switch (choice) {
        }








        System.out.println("Task was removed.");
        }
        return Controller.MAIN_MENU_ACTION;
    }
