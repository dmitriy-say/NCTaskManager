package ua.edu.sumdu.j2se.say.tasks;

public class ArrayTaskList {
    private Task[] taskList;
    private int taskAmount;

    /**
     * Конструктор, що створює список задач
     * з кількістю 0 (за замовчуванням).
     */
    public ArrayTaskList() {
        taskList = new Task[0];
    }
    public ArrayTaskList(int size) {
        taskList = new Task[size];
    }

    public void add(Task task) {
        if (task != null) {
            if (taskAmount == taskList.length) {
                Task[] newTaskList = new Task[(int) (taskList.length * 1.5 + 1)];
                System.arraycopy(taskList, 0, newTaskList, 0, taskAmount);
                taskList = newTaskList;
            }
            taskList[taskAmount] = task;
            taskAmount++;
        }
    }

    public boolean remove(Task task) {
        if (task != null) {
            for (int i = 0; i < taskAmount; i++) {
                if (taskList[i].equals(task)) {
                    System.arraycopy(taskList, (i + 1), taskList, i, taskAmount - (i + 1));
                    taskList[taskAmount - 1] = null;
                    taskAmount--;
                    return true;
                }
                if (taskList.length / taskAmount > 1.5) {
                    Task[] newTaskList = new Task[(int) (taskList.length / 1.5) + 1];
                    System.arraycopy(taskList, 0, newTaskList, 0, taskAmount);
                    taskList = newTaskList;
                }
            }
        }

        return false;
    }

    public int size() {
        return taskAmount;
    }

    public Task getTask(int i) {
        return taskList[i];
    }

    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList fromTo = new ArrayTaskList();
        for (int i = 0; i < taskAmount; i++) {
            if (getTask(i).nextTimeAfter(from) > from && getTask(i).nextTimeAfter(from) < to) {
                fromTo.add(getTask(i));
            }
        }
        return fromTo;
    }

}
