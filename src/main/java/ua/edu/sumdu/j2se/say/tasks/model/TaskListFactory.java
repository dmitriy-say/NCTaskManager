package ua.edu.sumdu.j2se.say.tasks.model;


import static ua.edu.sumdu.j2se.say.tasks.model.ListTypes.types.ARRAY;

public class TaskListFactory {

    public ListTypes.types type;

    public static AbstractTaskList createTaskList(ListTypes.types type) {
        if (type == ARRAY) {
            return new ArrayTaskList();
        } else {
            return new LinkedTaskList();
        }
    }
    public static ListTypes.types getType(AbstractTaskList abstractTaskList){
        return abstractTaskList.type;
    }
}
