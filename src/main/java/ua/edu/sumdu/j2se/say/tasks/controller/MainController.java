package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.view.*;

import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {

    public AbstractTaskList taskList;
    private List<Controller> controllers = new ArrayList<>();

    public MainController(AbstractTaskList taskList, View mainView) {
        super(mainView, Controller.MAIN_MENU_ACTION);

        this.taskList = taskList;

        controllers.add(this);
        controllers.add(new AllTasksController(new AllTasksView()));
        controllers.add(new AddTaskController(new AddTaskView(taskList)));
        controllers.add(new RemoveTaskController(new RemoveTaskView(taskList)));
        controllers.add(new ChangeTaskController(new ChangeTaskView(taskList)));
        controllers.add(new CalendarController(new CalendarView(), Controller.CALENDAR_ACTION));

        Notificator notificator = new Notificator(taskList);
        notificator.setDaemon(true);
        notificator.start();
    }

    @Override
    public int execute(AbstractTaskList taskList) {
        int action = view.printInfo(taskList);
        do {
            for (Controller controller : controllers) {
                if (controller.canToExecute(action)) {
                    action = controller.execute(this.taskList);
                }
            }
        } while (action != FINISH_ACTION);

        return FINISH_ACTION;
    }
}
