package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.view.View;

public class RemoveTaskController extends Controller {

    public RemoveTaskController(View removeTaskView) {
        super(removeTaskView, Controller.REMOVE_TASK_ACTION);
    }

    @Override
    public int execute(AbstractTaskList taskList) {
        return super.execute(taskList);
    }

}
