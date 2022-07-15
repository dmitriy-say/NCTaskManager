package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;

public class RemoveTaskView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Task was removed.");
        return Controller.MAIN_MENU_ACTION;
    }
}
