package ua.edu.sumdu.j2se.say.tasks;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.controller.MainController;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.model.ListTypes;
import ua.edu.sumdu.j2se.say.tasks.model.TaskListFactory;
import ua.edu.sumdu.j2se.say.tasks.view.MainView;
import ua.edu.sumdu.j2se.say.tasks.view.View;

public class NCTaskManager {
    private static final Logger log = LogManager.getLogger(NCTaskManager.class);


    public void start() {
        log.info("Starting Task Manager application...");
        System.out.println("TaskManager was started.");
        log.info("Creating generalTaskList (type = ARRAY)...");
        AbstractTaskList taskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        View mainView = new MainView();
        Controller mainController = new MainController(taskList, mainView);
        mainController.execute(null);
        System.out.println("Task Manager was closed.");
    }
}
