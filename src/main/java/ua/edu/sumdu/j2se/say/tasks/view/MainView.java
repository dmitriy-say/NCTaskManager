package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.controller.Notificator;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import static ua.edu.sumdu.j2se.say.tasks.model.Tasks.incoming;

public class MainView implements View {
    public final AbstractTaskList taskList;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public MainView(AbstractTaskList taskList) {
        this.taskList = taskList;
    }


    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("====================================NCTaskManager==========================================");
        System.out.println("------------------------------Incoming tasks for 24 hours----------------------------------");
        System.out.println(incoming(taskList, LocalDateTime.now(), LocalDateTime.now().plusHours(24)));
        System.out.println("-------------------------------------------------------------------------------------------");
        System.out.println("Make your choice (type number and press ENTER)!");
        System.out.println("1 - View all tasks.");
        System.out.println("2 - Add new task.");
        System.out.println("3 - Remove task.");
        System.out.println("4 - Change task.");
        System.out.println("5 - View calendar.");
        System.out.println("6 - Exit.");

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(reader.readLine());
                if (choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6) {
                    break;
                } else {
                    System.out.println("Try again!");
                    System.out.println("Make your choice (type number and press ENTER)!");
                    System.out.println("1 - View all tasks.");
                    System.out.println("2 - Add new task.");
                    System.out.println("3 - Remove task.");
                    System.out.println("4 - Change task.");
                    System.out.println("5 - View calendar.");
                    System.out.println("6 - Exit.");
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Try again!");
                System.out.println("Make your choice (type number and press ENTER)!");
                System.out.println("1 - View all tasks.");
                System.out.println("2 - Add new task.");
                System.out.println("3 - Remove task.");
                System.out.println("4 - Change task.");
                System.out.println("5 - View calendar.");
                System.out.println("6 - Exit.");
            }
        }
        return choice;
    }

}
