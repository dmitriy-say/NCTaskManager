package ua.edu.sumdu.j2se.say.tasks;

public class LinkedTaskList {
    private Node head;
    private int size;

    private static class Node {
        private final Task task;
        private Node next;

        public Node(Task task) {
            this.task = task;
        }
    }

    public void add(Task task) {
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

    public boolean remove(Task task) {
        if (head == null) {
            return false;
        } else if (head.task.equals(task)) {
                head = head.next;
                size--;
                return true;
        } else {
            Node currentNode = head;
            while (currentNode.next != null) {
                if (currentNode.next.task.equals(task)){
                    currentNode.next = currentNode.next.next;
                    size--;
                    return true;
                }
                currentNode = currentNode.next;
            }
        }
        return false;
    }

    public int size() { return size; }

    public Task getTask(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index is outside the list");
        }
        Node currentNode  = head;
        int i = 0;
        while (currentNode.next != null){
            if (i == index) break;
            currentNode = currentNode.next;
            i++;
        }
        return currentNode.task;
    }
    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList fromTo = new LinkedTaskList();
        if (from < 0) {
            throw new IllegalArgumentException("The time cannot be less than zero!");
        } else if (from > to) {
            throw new IllegalArgumentException("Time \"to\" must be greater than \"from\"!");
        } else {
            for (int i = 0; i < size; i++) {
                if (getTask(i).nextTimeAfter(from) > from && getTask(i).nextTimeAfter(from) < to) {
                    fromTo.add(getTask(i));
                }
            }
            return fromTo;
        }
    }
}
