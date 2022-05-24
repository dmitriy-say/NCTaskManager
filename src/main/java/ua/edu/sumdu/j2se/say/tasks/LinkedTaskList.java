package ua.edu.sumdu.j2se.say.tasks;

public class LinkedTaskList {
    private Node firstTask;
    private Node lastTask;
    private int size;

    private static class Node {
        Task task;
        Node nextTask;
        Node previousTask;

        public Node(Task task) {
            this.task = task;
        }
    }

    public void add(Task task) {
        Node newNode = new Node(task);
        if (firstTask == null) {
            newNode.nextTask = null;
            newNode.previousTask = null;
            firstTask = newNode;
        } else {
            lastTask.nextTask = newNode;
            newNode.previousTask = lastTask;
        }
        lastTask = newNode;
        size++;
    }


    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node result = firstTask;
        for (int i = 0; i < index; i++) {
            result = result.nextTask;
        }

        return result.task;
    }
}
/*
    public boolean remove(Task task) {

        if (size == 0) {
            return false;
        } else if (size == 1) {
            firstTask = null;
            lastTask = null;
            size = 0;
            return true;
        }

        Node nodeBefore = findNodeBefore(task);

        if (nodeBefore.task.equals(null)) {
            firstTask = firstTask.nextTask;
            size--;
            return true;
        } else if (!nodeBefore.task.equals(null)) {
            if (lastTask.task == task) {
                nodeBefore.nextTask = null;
                lastTask = nodeBefore;
            } else {
                nodeBefore.nextTask = nodeBefore.nextTask.nextTask;
            }
            size--;
            return true;
        }
        return false;
    }

    private Node findNodeBefore(Task task) {
        if (firstTask.task.equals(task)) {
            return new Node(task);
        }

        Node node = firstTask;
        while (!node.nextTask.equals(null)) {
            if (node.nextTask.task.equals(task)) {
                return node;
            }
            node = node.nextTask;
        }
        return null;
    }

    private Node findNodeBeforeByIndex(int index) {
        if (index <= 0 || index > size - 1) {
            return null;
        }

        int count = 0;
        Node node = first;
        while (node.next != null) {
            if (count == index - 1) {
                return node;
            }
            count++;
            node = node.next;
        }
        return null;
    }
}
*/