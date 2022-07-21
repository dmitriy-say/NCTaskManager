package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.view.View;

public class ChangeTaskController extends Controller {

    public ChangeTaskController(View removeTaskView) {
        super(removeTaskView, Controller.CHANGE_TASK_ACTION);
    }

    @Override
    public int execute(AbstractTaskList taskList) {
        return super.execute(taskList);
    }

}
