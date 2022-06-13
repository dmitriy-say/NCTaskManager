package ua.edu.sumdu.j2se.say.tasks;

import static ua.edu.sumdu.j2se.say.tasks.ListTypes.types.ARRAY;

public class TaskListFactory {


    public static AbstractTaskList createTaskList(final ListTypes.types type) {
        if (type == ARRAY) {
            return new ArrayTaskList();
        } else {
            return new LinkedTaskList();
        }
    }
}
