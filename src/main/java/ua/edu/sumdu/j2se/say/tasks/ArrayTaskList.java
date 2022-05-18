package ua.edu.sumdu.j2se.say.tasks;

public class ArrayTaskList {
    private Task[] taskList;
    private int taskAmount;

    /**
     * Конструктор, що створює список задач
     * з кількістю 10 (за замовчуванням).
     */
    public ArrayTaskList() {
        taskList = new Task[10];
    }

    /**
     * Конструктор, що створює список задач
     * з необхідною кількістю.
     * 
     * @param size - розмір списку.
     */
    public ArrayTaskList(int size) {
        taskList = new Task[size];
    }

    public void add(Task task) {
        if (task != null) {
            if (taskAmount == taskList.length) {
                Task[] newTaskList = new Task[(int) (taskList.length * 1.5 + 1)];
                System.arraycopy(taskList, 0, newTaskList, 0, size());
                taskList = newTaskList;
            }
            taskList[taskAmount] = task;
            taskAmount++;
        }
    }

    public boolean remove(Task task) {
        if (task != null) {
            for (int i = 0; i < size(); i++) {
                if (getTask(i).equals(task)) {
                    System.arraycopy(taskList, (i + 1), taskList, i, size() - (i + 1));
                    taskList[taskAmount - 1] = null;
                    taskAmount--;
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        return taskAmount;
    }

    Task getTask(int i) {
        return taskList[i];
    }

}
