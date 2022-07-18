package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.view.View;

public class RemoveTaskController extends Controller {
    protected int actionToExecute = 3;
    public RemoveTaskController(View removeTaskView, int actionToPerform) {
        super(removeTaskView, Controller.REMOVE_TASK_ACTION);
    }
}
