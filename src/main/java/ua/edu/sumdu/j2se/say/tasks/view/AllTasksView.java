package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;

public class AllTasksView implements View {


    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println(taskList);
        return 0;
    }
}