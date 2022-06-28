package ua.edu.sumdu.j2se.say.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public abstract class AbstractTaskList
        extends TaskListFactory
        implements Iterable<Task>, Cloneable, Serializable {
    /**
     * Type of the list to create.
     */
    protected ListTypes.types type;
    /**
     * Number of tasks in the list.
     */
    protected transient int size;
    protected transient int modCount = 0;
    /**
     * Adds the task to the list.
     * @param task - task to add.
     */
    public abstract void add(Task task);
    /**
     * Removes the task from the list.
     * @param task - task to remove.
     * @return true if task removed, false if task not found.
     */
    public abstract boolean remove(Task task);
    /**
     * Returns the list's size.
     * @return number of tasks in the list.
     */
    public int size() {
        return size;
    }
    /**
     * Returns array's length when ArrayTaskList used.
     * @return taskList.length
     */
    public abstract int thisArraySize();
    /**
     * Gets the task from the list by the index.
     * @param index - index of the task tj get.
     * @return task.
     */
    public abstract Task getTask(int index);
    public abstract Stream<Task> getStream();



    public AbstractTaskList clone() throws CloneNotSupportedException {
        return (AbstractTaskList) super.clone();
    }
    public String toString() {
        return "AbstractTaskList{"
                + "type=" + type
                + ", size=" + size
                + '}';
    }

    // -----------------------Iterators---------------------------------

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @implSpec
     * This implementation returns a straightforward implementation of the
     * iterator interface, relying on the backing list's {@code size()},
     * {@code get(int)}, and {@code remove(int)} methods.
     *
     * <p>Note that the iterator returned by this method will throw an
     * {@link UnsupportedOperationException} in response to its
     * {@code remove} method unless the list's {@code remove(int)} method is
     * overridden.
     *
     * <p>This implementation can be made to throw runtime exceptions in the
     * face of concurrent modification, as described in the specification
     * for the (protected) {@link #modCount} field.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public abstract Iterator<Task> iterator();
    /**
     * {@inheritDoc}
     *
     * @implSpec
     * This implementation returns a straightforward implementation of the
     * {@code ListIterator} interface that extends the implementation of the
     * {@code Iterator} interface returned by the {@code iterator()} method.
     * The {@code ListIterator} implementation relies on the backing list's
     * {@code get(int)}, {@code set(int, E)}, {@code add(int, E)}
     * and {@code remove(int)} methods.
     *
     * <p>Note that the list iterator returned by this implementation will
     * throw an {@link UnsupportedOperationException} in response to its
     * {@code remove}, {@code set} and {@code add} methods unless the
     * list's {@code remove(int)}, {@code set(int, E)}, and
     * {@code add(int, E)} methods are overridden.
     *
     * <p>This implementation can be made to throw runtime exceptions in the
     * face of concurrent modification, as described in the specification for
     * the (protected) {@link #modCount} field.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public abstract ListIterator<Task> listIterator(final int index);
    public abstract ListIterator<Task> listIterator();

    // ------------------Service methods----------------------------

    /**
     * Tells if the argument is the index of a valid position for an
     * iterator or an add operation.
     */
    protected void checkPositionIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size();
    }
    protected void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    // ---------------Comparison and hashing-------------------------

    /**
     * Compares the specified object with this list for equality.  Returns
     * {@code true} if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements {@code e1} and
     * {@code e2} are <i>equal</i> if {@code (e1==null ? e2==null :
     * e1.equals(e2))}.)  In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.
     *
     * @implSpec
     * This implementation first checks if the specified object is this
     * list. If so, it returns {@code true}; if not, it checks if the
     * specified object is a list. If not, it returns {@code false}; if so,
     * it iterates over both lists, comparing corresponding pairs of elements.
     * If any comparison returns {@code false}, this method returns
     * {@code false}.  If either iterator runs out of elements before the
     * other it returns {@code false} (as the lists are of unequal length);
     * otherwise it returns {@code true} when the iterations complete.
     *
     * @param o the object to be compared for equality with this list
     * @return {@code true} if the specified object is equal to this list
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AbstractTaskList))
            return false;

        ListIterator<Task> e1 = listIterator();
        ListIterator<Task> e2 = ((AbstractTaskList) o).listIterator();
        while (e1.hasNext() && e2.hasNext()) {
            Task o1 = e1.next();
            Task o2 = e2.next();
            if (!(o1.equals(o2)))
                return false;
        }
        return !(e1.hasNext() || e2.hasNext());
    }
    /**
     * Returns the hash code value for this list.
     *
     * @implSpec
     * This implementation uses exactly the code that is used to define the
     * list hash function in the documentation for the {@link List#hashCode}
     * method.
     *
     * @return the hash code value for this list
     */
    public int hashCode() {
        int hashCode = 1;
        for (Task e : this)
            hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
        return hashCode;
    }










}

