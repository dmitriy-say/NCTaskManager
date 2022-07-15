package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {

    private AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<>();

    public MainController(AbstractTaskList taskList, View mainView) {
        super(mainView, Controller.MAIN_MENU_ACTION);

        controllers.add(this);
        controllers.add(new CreateNewTaskListController(new CreateNewTaskListView(), Controller.EMPTY_LIST_ACTION));
        controllers.add(new AddTaskController(new AddTaskView(), Controller.ADD_TASK_ACTION));
        controllers.add(new RemoveTaskController(new RemoveTaskView(), Controller.REMOVE_TASK_ACTION));
        controllers.add(new CalendarController(new CalendarView(), Controller.CALENDAR_ACTION));
    }

    @Override
    public int execute(AbstractTaskList taskList) {
        int action = view.printInfo(taskList);
        for (;;){
            for (Controller controller : controllers) {
                if (controller.canToExecute(action)) {
                    action = controller.execute(this.taskList);
                }
            }
            if (action == FINISH_ACTION) {
                break;
            }
        }

        return FINISH_ACTION;
    }
}
