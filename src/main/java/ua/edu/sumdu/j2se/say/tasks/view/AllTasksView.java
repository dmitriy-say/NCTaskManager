package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;

public class AllTasksView implements View {


    @Override
    public int printInfo(AbstractTaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("Tasks list is empty!");
        }
        System.out.println(taskList);
        return Controller.MAIN_MENU_ACTION;
    }
}
