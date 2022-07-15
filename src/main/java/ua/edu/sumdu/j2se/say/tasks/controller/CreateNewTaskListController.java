package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.view.View;

public class CreateNewTaskListController extends Controller {
    public CreateNewTaskListController(View createNewTaskListView, int actionToPerform) {
        super(createNewTaskListView, Controller.EMPTY_LIST_ACTION);
    }
}
