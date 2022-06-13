package ua.edu.sumdu.j2se.say.tasks;

import static ua.edu.sumdu.j2se.say.tasks.ListTypes.types.LINKED;

public class LinkedTaskList extends AbstractTaskList {
    /**
     * The first node of the list.
     */
    private Node head;


    private static class Node {
        /**
         * The task node includes.
         */
        private final Task task;
        /**
         * Next node reference.
         */
        private Node next;

        Node(final Task task) {
            this.task = task;
        }
    }

    /**
     * The constructor needed only to make this type LINKED.
     */
    public LinkedTaskList() {
        this.type = LINKED;
    }

    /**
     * This method can add a task to the list.
     * @param task to be added.
     */
    public void add(final Task task) {
        if (head == null) {
            head = new Node(task);
        } else {
            Node currentNode = head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = new Node(task);
        }
        size++;
    }

    /**
     * This method can remove task from the list.
     * @param task should be removed.
     * @return true if task removed, false if task not found.
     */
    public boolean remove(final Task task) {
        if (head == null) {
            return false;
        } else if (head.task.equals(task)) {
                head = head.next;
                size--;
                return true;
        } else {
            Node currentNode = head;
            while (currentNode.next != null) {
                if (currentNode.next.task.equals(task)) {
                    currentNode.next = currentNode.next.next;
                    size--;
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    /**
     * This method used only for arraylist.
     */
    @Override
    public int thisArraySize() {
        return 0;
    }


    /**
     * This method can get the task from the
     * linked list by its pseudo-index.
     * @param index - tasks pseudo-index.
     * @return task.
     */
    public Task getTask(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "The index is outside the list");
        }
        Node currentNode  = head;
        int i = 0;
        while (currentNode.next != null) {
            if (i == index) {
                break;
            }
                currentNode = currentNode.next;
                i++;
        }
        return currentNode.task;
    }

}
