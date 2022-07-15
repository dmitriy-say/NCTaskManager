package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.view.View;

public class AddTaskController extends Controller {
    public AddTaskController(View addTaskView, int actionToExecute) {
        super(addTaskView, Controller.ADD_TASK_ACTION);
    }
}
