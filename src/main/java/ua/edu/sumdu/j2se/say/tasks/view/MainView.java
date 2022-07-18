package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView implements View {
    public final AbstractTaskList taskList;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public MainView(AbstractTaskList taskList) {
        this.taskList = taskList;
    }


    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Make your choice (type number and press ENTER)!");
        System.out.println("1 - View all tasks.");
        System.out.println("2 - Add new task.");
        System.out.println("3 - Remove task.");
        System.out.println("4 - View calendar.");
        System.out.println("5 - Exit.");
        int choice = 0;
        try {
            choice = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    if (choice == 1 | choice == 2 | choice == 3 | choice == 4 | choice == 5) {
        return choice;
    } else {
        System.out.println("Type 1, 2, 3, 4 or 5 and press Enter!");
        return 0;
    }
    }
}
