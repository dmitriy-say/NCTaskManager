package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.view.View;

public class AddTaskController extends Controller {
    public AddTaskController(View addTaskView) {
        super(addTaskView, Controller.ADD_TASK_ACTION);
    }

    @Override
    public int execute(AbstractTaskList taskList) {
        return super.execute(taskList);
    }
}
