package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AllTasksView implements View {


    @Override
    public int printInfo(AbstractTaskList taskList) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String choice = "";
        while (!choice.equals("0")) {
            try {
                if (taskList.size() == 0) {
                    System.out.println("Tasks list is empty! There are no tasks to display!");
                    System.out.println("Type 0 and press ENTER to GO BACK!");
                } else {
                    System.out.println("Displaying all tasks in the list:");
                    System.out.println(taskList);
                    System.out.println("Type 0 and press ENTER to GO BACK!");
                }
                choice = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Controller.MAIN_MENU_ACTION;
    }
}
