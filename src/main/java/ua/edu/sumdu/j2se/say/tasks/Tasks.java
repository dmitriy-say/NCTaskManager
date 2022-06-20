package ua.edu.sumdu.j2se.say.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class Tasks {
    public static Iterable<Task> incoming(
            Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {

        if ( start.isBefore(LocalDateTime.MIN) ) {
            throw new IllegalArgumentException(
                    "Start is too far away in the past!" +
                            "Let`s start little later, please!!!");
        }
        if ( start.isAfter(end) ) {
            throw new IllegalArgumentException(
                    "Start must be before end! " +
                            "And end must be after start! " +
                            "Don`t you know? " +
                            "Remember it and try again!!!");
        }
        if ( end.isAfter(LocalDateTime.MAX) ) {
            throw new IllegalArgumentException(
                    "End is too far away in the future! " +
                            "Let`s end little earlier, please!!!");
        }

        AbstractTaskList incomingTaskList = TaskListFactory
                .createTaskList(TaskListFactory.getType((AbstractTaskList) tasks));
        Stream<Task> stream = ((AbstractTaskList) tasks).getStream();

        stream.filter(Objects::nonNull)
                .filter(task -> task.nextTimeAfter(start) != null)
                .filter(task -> task.nextTimeAfter(start).isAfter(start))
                .filter(task -> task.nextTimeAfter(start).isBefore(end)
                        || task.nextTimeAfter(start).equals(end)).forEach(incomingTaskList::add);

        return incomingTaskList;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(
            Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {

        if ( start.isBefore(LocalDateTime.MIN) ) {
            throw new IllegalArgumentException(
                    "Start is too far away in the past!" +
                            "Let`s start little later, please!!!");
        }
        if ( start.isAfter(end) ) {
            throw new IllegalArgumentException(
                    "Start must be before end! " +
                            "And end must be after start! " +
                            "Don`t you know? " +
                            "Remember it and try again!!!");
        }
        if ( end.isAfter(LocalDateTime.MAX) ) {
            throw new IllegalArgumentException(
                    "End is too far away in the future! " +
                            "Let`s end little earlier, please!!!");
        }

        SortedMap<LocalDateTime, Set<Task>> sortedMap = new TreeMap<>();
        Set<Task> set;
        LocalDateTime taskNextTimeStarts;

        for (Task task : tasks) {
            taskNextTimeStarts = task.nextTimeAfter(start);
            while (taskNextTimeStarts != null && !taskNextTimeStarts.isAfter(end)) {
                if (sortedMap.containsKey(taskNextTimeStarts)) {
                    sortedMap.get(taskNextTimeStarts).add(task);
                } else {
                    set = new HashSet<>();
                    set.add(task);
                    sortedMap.put(taskNextTimeStarts, set);
                }
                taskNextTimeStarts = task.nextTimeAfter(taskNextTimeStarts);
            }
        }
        return sortedMap;
    }
}
