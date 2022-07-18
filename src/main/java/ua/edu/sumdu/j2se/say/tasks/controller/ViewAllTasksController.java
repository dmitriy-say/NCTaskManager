package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.view.View;

public class ViewAllTasksController extends Controller {
    protected int actionToExecute = 1;
    public ViewAllTasksController(View allTasksView, int actionToPerform) {
        super(allTasksView, Controller.VIEW_ALL_TASKS_ACTION);
    }

    @Override
    public int execute(AbstractTaskList taskList) {
        return super.execute(taskList);
    }
}
