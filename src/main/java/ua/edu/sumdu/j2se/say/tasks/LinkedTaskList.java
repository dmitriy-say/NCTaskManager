package ua.edu.sumdu.j2se.say.tasks;

import java.util.*;
import java.util.function.Consumer;

import static ua.edu.sumdu.j2se.say.tasks.ListTypes.types.LINKED;

public class LinkedTaskList extends AbstractTaskList implements Cloneable {
    private transient Node<Task> first;
    private transient Node<Task> last;

    /**
     * The constructor needed only to make this type LINKED.
     */
    public LinkedTaskList() {
        this.type = LINKED;
    }
    public void add(Task task) {
        final Node<Task> l = last;
        final Node<Task> newNode = new Node<>(l, task, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }
    public boolean remove(Task task) {
        if (task == null) {
            for (Node<Task> x = first; x != null; x = x.next) {
                if (x.task == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<Task> x = first; x != null; x = x.next) {
                if (task.equals(x.task)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }
    public Task getTask(final int index) {
        checkElementIndex(index);
        return node(index).task;
    }
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList clone = (LinkedTaskList) super.clone();
        // Put clone into "virgin" state
        clone.first = clone.last = null;
        clone.size = 0;
        clone.modCount = 0;
        // Initialize clone with our elements
        for (Node<Task> x = first; x != null; x = x.next)
            clone.add(x.task);
        return clone;
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    //------------------------ Iterators --------------------------

    public ListIterator<Task> listIterator(int index) {
        checkPositionIndex(index);
        return new LinkedTaskList.ListItr(index);
    }
    public ListIterator<Task> listIterator() {
        return new LinkedTaskList.ListItr(0);
    }
    public Iterator<Task> iterator() {
        return new LinkedTaskList.DescendingIterator();
    }
    private class ListItr implements ListIterator<Task> {
        private LinkedTaskList.Node<Task> lastReturned;
        private LinkedTaskList.Node<Task> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            assert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public Task next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.task;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public Task previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.task;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            LinkedTaskList.Node<Task> lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
        }

        public void set(Task task) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.task = task;
        }

        public void add(Task task) {
            checkForComodification();
            lastReturned = null;
            if (next == null)
                linkLast(task);
            else
                linkBefore(task, next);
            nextIndex++;
            expectedModCount++;
        }

        public void forEachRemaining(Consumer<? super Task> action) {
            Objects.requireNonNull(action);
            while (modCount == expectedModCount && nextIndex < size) {
                action.accept(next.task);
                lastReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodification();
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }

        private void checkPositionIndex(int index) {
            if (!isPositionIndex(index))
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private boolean isPositionIndex(int index) {
            return index >= 0 && index <= size;
        }
    }
    /**
     * Adapter to provide descending iterators via ListItr.previous
     */
    private class DescendingIterator implements Iterator<Task> {
        private final LinkedTaskList.ListItr itr = new LinkedTaskList.ListItr(size());
        public boolean hasNext() {
            return itr.hasPrevious();
        }
        public Task next() {
            return itr.previous();
        }
        public void remove() {
            itr.remove();
        }
    }

    //------------- Service methods --------------------------

    private void unlink(Node<Task> x) {
        final Node<Task> next = x.next;
        final Node<Task> prev = x.prev;
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.task = null;
        size--;
        modCount++;
    }
    /**
     * Links task as last element.
     */
    void linkLast(Task task) {
        final LinkedTaskList.Node<Task> l = last;
        final LinkedTaskList.Node<Task> newNode = new LinkedTaskList.Node<>(l, task, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }
    /**
     * Inserts element e before non-null Node taskNode.
     */
    void linkBefore(Task task, LinkedTaskList.Node<Task> taskNode) {
        // assert taskNode != null;
        final LinkedTaskList.Node<Task> pre = taskNode.prev;
        final LinkedTaskList.Node<Task> newNode = new LinkedTaskList.Node<>(pre, task, taskNode);
        taskNode.prev = newNode;
        if (pre == null)
            first = newNode;
        else
            pre.next = newNode;
        size++;
        modCount++;
    }
    /**
     * This method used only for arraylist.
     */
    public int thisArraySize() {
        return -1;
    }
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }
    Node<Task> node(int index) {
        if (index < (size >> 1)) {
            Node<Task> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<Task> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
    private static class Node<Task> {
        private Task task;
        private Node<Task> next;
        private Node<Task> prev;

        Node(Node<Task> prev, Task task, Node<Task> next) {
            this.task = task;
            this.next = next;
            this.prev = prev;
        }
    }

}




