package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.view.View;

public abstract class Controller {

    public static final int MAIN_MENU_ACTION = 0;
    public static final int VIEW_ALL_TASKS_ACTION = 1;
    public static final int ADD_TASK_ACTION = 2;
    public static final int REMOVE_TASK_ACTION = 3;
    public static final int CALENDAR_ACTION = 4;
    public static final int FINISH_ACTION = 5;

    protected View view;
    protected int actionToExecute = 0;

    public Controller(View view, int actionToExecute) {
        this.view = view;
        this.actionToExecute = actionToExecute;
    }

    public boolean canToExecute(int action) {
        return (action == actionToExecute);
    }

    public int execute (AbstractTaskList taskList) {
        return view.printInfo(taskList);
    }
}
