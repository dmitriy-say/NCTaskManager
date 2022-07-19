package ua.edu.sumdu.j2se.say.tasks;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.controller.MainController;
import ua.edu.sumdu.j2se.say.tasks.model.*;
import ua.edu.sumdu.j2se.say.tasks.view.MainView;
import ua.edu.sumdu.j2se.say.tasks.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NCTaskManager {
    private static final Logger log = LogManager.getLogger(NCTaskManager.class);
    public static File tasksFile, textFile;

    public NCTaskManager() {
        tasksFile=new File("SavedTasks.dat");
        textFile = new File("SavedText.dat");
    }

    public static File getTasksFile() {
        return tasksFile;
    }

    public static File getTextFile() {
        return textFile;
    }

    public void start() {
        log.info("Starting Task Manager application...");
        System.out.println("TaskManager was started.");
        log.info("Creating taskList (type = ARRAY)...");
        AbstractTaskList taskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        if (tasksFile.length()!=0) {
            TaskIO.readBinary(taskList, tasksFile);
        } else {
            System.out.println("tasksFile is empty!");
        }
        View mainView = new MainView(taskList);
        Controller mainController = new MainController(taskList, mainView);
        mainController.execute(taskList);

        System.out.println("Task Manager was closed.");
    }
}
