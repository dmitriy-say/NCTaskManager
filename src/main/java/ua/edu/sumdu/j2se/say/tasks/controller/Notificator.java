package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.model.Task;
import ua.edu.sumdu.j2se.say.tasks.model.Tasks;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class Notificator extends Thread {
    private final AbstractTaskList taskList;
    private static final long NOTIFICATION_PERIOD = 3600;
    private static final long SLEEPING_TIME = 300000;

    public Notificator(AbstractTaskList taskList) {
        this.taskList = taskList;
    }

    public void run() {
        SortedMap<LocalDateTime, Set<Task>> t;
        while (true) {
            t = Tasks.calendar(taskList, LocalDateTime.now(), LocalDateTime.now().plusSeconds(NOTIFICATION_PERIOD));
            if (!t.isEmpty()) {
                System.out.println("====================================================================================");
                System.out.println("There are tasks to be performed in 1 hour:");
                System.out.println(t);
                System.out.println("====================================================================================");
                try {
                    sleep(SLEEPING_TIME);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }

        }
    }
}
