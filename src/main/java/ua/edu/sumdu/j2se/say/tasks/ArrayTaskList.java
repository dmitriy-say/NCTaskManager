package ua.edu.sumdu.j2se.say.tasks;

import java.util.*;
import java.util.function.Consumer;

import static ua.edu.sumdu.j2se.say.tasks.ListTypes.types.ARRAY;

public class ArrayTaskList extends AbstractTaskList implements Cloneable {
    /**
     * Array to save tasks.
     */
    private Task[] taskList;
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * Creates a list of 10 tasks (default).
     * When you add tasks to the list, its capacity expands.
     */
    public ArrayTaskList() {
        taskList = new Task[DEFAULT_CAPACITY];
        this.type = ARRAY;
    }
    /**
     * Adds the specified task to the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        if (size == taskList.length) {
            Task[] newTaskList =
                    new Task[(int) (taskList.length * 1.5 + 1)];
            System.arraycopy(taskList, 0, newTaskList, 0, size);
            taskList = newTaskList;
        }
        taskList[size] = task;
        size++;
        modCount++;
    }
    public void add(int index, Task task) {
        if (size == taskList.length) {
            Task[] newTaskList =
                    new Task[(int) (taskList.length * 1.5 + 1)];
            System.arraycopy(taskList, 0, newTaskList, 0, size);
            taskList = newTaskList;
        }
        taskList[index] = task;
        size++;
        modCount++;
    }
    /**
     * Removes a task from the list and returns the truth,
     * if such a task was on the list. If there were several on the list
     * such tasks, the first of them is removed.
     *
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
                modCount++;
                return true;
            }
            if ((float) taskList.length / size > 1.5) {
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
     * Returns the task that is on specified place in the list.
     * The first task's index is 0.
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
    public Task set(int index, Task task) {
        checkPositionIndex(index);
        Task oldValue = taskList[index];
        taskList[index] = task;
        return oldValue;
    }
    /**
     * Returns array's length.
     * @return taskList.length
     */
    public int thisArraySize() {
        return taskList.length;
    }
    /**
     * Returns the hash code value for this list.
     * The hash code of a list is defined to be the result of the following calculation:
     *  int hashCode = 1;
     *  for (Task e : taskList)
     *  hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
     * This ensures that taskList1.equals(taskList2) implies that
     * taskList1.hashCode()==taskList2.hashCode() for any two lists,
     * taskList1 and taskList2, as required by the general contract of Object.hashCode.
     */
    public int hashCode() {
        return super.hashCode();
    }
    /**
     * Returns a shallow copy of this {@code ArrayList} instance.  (The
     * elements themselves are not copied.)
     * @return a clone of this {@code ArrayList} instance
     */
    public ArrayTaskList clone() throws CloneNotSupportedException {
            ArrayTaskList v = (ArrayTaskList) super.clone();
            v.taskList = Arrays.copyOf(taskList, size);
            v.modCount = 0;
            return v;

    }
    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    // ---------------------------------Iterators-------------------------------------

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public ListIterator<Task> listIterator(int index) {
        checkPositionIndex(index);
        return new ArrayTaskList.ListItr(index);
    }
    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * <p>The returned list iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @see #listIterator(int)
     */
    public ListIterator<Task> listIterator() {
        return new ArrayTaskList.ListItr(0);
    }
    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * <p>The returned iterator is <a href="#fail-fast"><i>fail-fast</i></a>.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<Task> iterator() {
        return new ArrayTaskList.Itr();
    }
    private class Itr implements Iterator<Task> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        // prevent creating a synthetic constructor
        Itr() {}

        public boolean hasNext() {
            return cursor != size;
        }

        public Task next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Task[] taskList1 = ArrayTaskList.this.taskList;
            if (i >= taskList1.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return taskList1[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayTaskList.this.remove(taskList[lastRet]);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super Task> action) {
            Objects.requireNonNull(action);
            final int size = ArrayTaskList.this.size;
            int i = cursor;
            if (i < size) {
                final Task[] es = taskList;
                if (i >= es.length)
                    throw new ConcurrentModificationException();
                for (; i < size && modCount == expectedModCount; i++)
                    action.accept(es[i]);
                // update once at end to reduce heap write traffic
                cursor = i;
                lastRet = i - 1;
                checkForComodification();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
    /**
     * An optimized version of AbstractList.ListItr
     */
    private class ListItr extends ArrayTaskList.Itr implements ListIterator<Task> {
        ListItr(int index) {
            super();
            cursor = index;
        }
        public boolean hasPrevious() {
            return cursor != 0;
        }
        public int nextIndex() {
            return cursor;
        }
        public int previousIndex() {
            return cursor - 1;
        }
        public Task previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Task[] elementData = ArrayTaskList.this.taskList;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return elementData[lastRet = i];
        }

        public void set(Task task) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayTaskList.this.set(lastRet, task);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(Task task) {
            checkForComodification();

            try {
                int i = cursor;
                ArrayTaskList.this.add(i, task);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }


}

