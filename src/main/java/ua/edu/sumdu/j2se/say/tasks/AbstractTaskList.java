package ua.edu.sumdu.j2se.say.tasks;


import static ua.edu.sumdu.j2se.say.tasks.TaskListFactory.createTaskList;

public abstract class AbstractTaskList {
    /**
     * Type of the list to create.
     */
    protected ListTypes.types type;
    /**
     * Number of tasks in the list.
     */
    protected int size;


    /**
     * Method to add the task tj the list.
     * @param task - task to add.
     */
    public abstract void add(Task task);

    /**
     * Method to remove the task from the list.
     * @param task - task to remove.
     * @return true if task removed, false if task not found.
     */
    public abstract boolean remove(Task task);

    /**
     * This method returns the list's size.
     * @return number of tasks in the list.
     */
    public int size() {
        return size;
    }

    /**
     * This method returns array's length.
     * @return taskList.length
     */
    public abstract int thisArraySize();

    /**
     * This method can get the task from the list by the index.
     * @param index - index of the task tj get.
     * @return task.
     */
    public abstract Task getTask(int index);

    /**
     * The method that returns a subset of tasks
     * that run over a specified period of time.
     * @param from - time from.
     * @param to - time to.
     * @return - subset of tasks.
     */
    public AbstractTaskList incoming(final int from, final int to) {
            if (from < 0) {
                throw new IllegalArgumentException(
                        "The time cannot be less than zero!");
            } else if (from > to) {
                throw new IllegalArgumentException(
                        "Time \"to\" must be greater than \"from\"!");
            } else {
                AbstractTaskList fromTo = createTaskList(type);
                for (int i = 0; i < size(); i++) {
                    if (getTask(i).nextTimeAfter(from) > from
                            && getTask(i).nextTimeAfter(from) < to) {
                        fromTo.add(getTask(i));
                    }
                }
                return fromTo;
            }
        }

    @Override
    public String toString() {
        return "AbstractTaskList{"
                + "type=" + type
                + ", size=" + size
                + '}';
    }

}

