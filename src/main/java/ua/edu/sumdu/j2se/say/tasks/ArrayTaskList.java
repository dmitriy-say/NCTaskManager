package ua.edu.sumdu.j2se.say.tasks;

public class ArrayTaskList {
    private Task[] taskList;
    private int taskAmount;

    /**
     * Конструктор, що створює список задач
     * з кількістю 0 (за замовчуванням). При додаванні
     * до списку задач його місткість розширюється.
     */
    public ArrayTaskList() {
        taskList = new Task[0];
    }
    /**
     * Метод, що додає до списку вказану задачу
     * @param task - задача, яку необхідно додати
     * до списку.
     */
    public void add(Task task) {
            if (taskAmount == taskList.length) {
                Task[] newTaskList = new Task[(int) (taskList.length * 1.5 + 1)];
                System.arraycopy(taskList, 0, newTaskList, 0, taskAmount);
                taskList = newTaskList;
            }
            taskList[taskAmount] = task;
            taskAmount++;
    }
    /**
     * Метод, що видаляє задачу зі списку і повертає істину,
     * якщо така задача була у списку. Якщо у списку було декілька
     * таких задач, видаляється перша з них.
     * @param task - задача, яку необхідно видалити.
     * @return - якщо така задача є у списку - true, якщо ні - false.
     */
    public boolean remove(Task task) {
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
        return false;
    }
    /**
     * Метод, що повертає кількість задач у списку.
     * @return taskAmount - кількість задач у списку.
     */
    public int size() { return taskAmount; }
    /**
     * Метод, що повертає мысткысть списку.
     * @return taskList.length - довжина масиву списку.
     */
    public int sizeAll() { return taskList.length; }
    /**
     * Метод, що повертає задачу, яка знаходиться на
     * вказаному місці у списку. Перша задача має індекс 0.
     * @param index - місце задачі у списку.
     * @return taskList[i].
     */
    public Task getTask(int index) {
        if (index < 0 || index > taskAmount-1) {
            throw new IndexOutOfBoundsException("The index is outside the array");
        }
        return taskList[index];
    }
    /**
     * Метод, що повертає підмножину задач, які заплановані
     * на виконання хоча б раз після часу from і не пізніше ніж to.
     * @param from - час, з якого відбираються задачі.
     * @param to - час, до якого відбираються задачі.
     * @return - список задач, які заплановані на виконання
     * від після часу from і не пізніше ніж to.
     */
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList fromTo = new ArrayTaskList();
        if (from < 0) {
            throw new IllegalArgumentException("The time cannot be less than zero!");
        } else if (from > to) {
            throw new IllegalArgumentException("Time \"to\" must be greater than \"from\"!");
        } else {
            for (int i = 0; i < taskAmount; i++) {
                if (getTask(i).nextTimeAfter(from) > from && getTask(i).nextTimeAfter(from) < to) {
                    fromTo.add(getTask(i));
                }
            }
            return fromTo;
        }
    }
}
