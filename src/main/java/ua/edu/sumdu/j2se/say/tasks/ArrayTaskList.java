package ua.edu.sumdu.j2se.say.tasks;

import static ua.edu.sumdu.j2se.say.tasks.ListTypes.types.ARRAY;

public class ArrayTaskList extends AbstractTaskList {
    /**
     * Array to save tasks.
     */
    private Task[] taskList;
    /**
     * Constructor that creates a list of 10 tasks (default).
     * When you add tasks to the list, its capacity expands.
     */
    public ArrayTaskList() {
        taskList = new Task[10];
        this.type = ARRAY;
    }
    /**
     * A method that adds the specified task to the list.
     * @param task - task to add
     */
    public void add(final Task task) {
            if (size == taskList.length) {
                Task[] newTaskList =
                        new Task[(int) (taskList.length * 1.5 + 1)];
                System.arraycopy(taskList, 0, newTaskList, 0, size);
                taskList = newTaskList;
            }
            taskList[size] = task;
            size++;
    }
    /**
     * A method that removes a task from the list and returns the truth,
     * if such a task was on the list. If there were several on the list
     * such tasks, the first of them is removed.
     * @param task - task to remove.
     * @return - if such a task is in the list - true, if not - false.
     */
    public boolean remove(final Task task) {
            for (int i = 0; i < size; i++) {
                if (taskList[i].equals(task)) {
                    System.arraycopy(taskList,
                            (i + 1), taskList, i, size - (i + 1));
                    taskList[size - 1] = null;
                    size--;
                    return true;
                }
                if ((float)taskList.length / size > 1.5) {
                    Task[] newTaskList =
                            new Task[(int) (taskList.length / 1.5) + 1];
                    System.arraycopy(taskList,
                            0, newTaskList, 0, size);
                    taskList = newTaskList;
                }
            }
        return false;
    }
    /**
     * Метод, що повертає задачу, яка знаходиться на
     * вказаному місці у списку. Перша задача має індекс 0.
     * @param index - місце задачі у списку.
     * @return taskList[i].
     */
    public Task getTask(final int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException(
                    "The index is outside the array");
        }
        return taskList[index];
    }

    /**
     * This method returns array's length.
     * @return taskList.length
     */
    public int thisArraySize() {
        return taskList.length;
    }
}




