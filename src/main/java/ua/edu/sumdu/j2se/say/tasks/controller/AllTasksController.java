package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.view.View;

public class AllTasksController extends Controller {
    public AllTasksController(View allTasksView) {
        super(allTasksView, Controller.VIEW_ALL_TASKS_ACTION);
    }

    @Override
    public int execute(AbstractTaskList taskList) {
        return super.execute(taskList);
    }
}
