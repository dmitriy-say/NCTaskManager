package ua.edu.sumdu.j2se.say.tasks.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class Tasks {
    public static Iterable<Task> incoming(
            Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (tasks == null){
            throw new IllegalArgumentException("The list is empty!!!");
        }
        if(start == null) {
            throw new IllegalArgumentException("Start is null!!!");
        }
        if (start.isBefore(LocalDateTime.MIN)) {
            throw new IllegalArgumentException("Start is too far away in the past!!!");
        }
        if (start.isAfter(LocalDateTime.MAX)) {
            throw new IllegalArgumentException("Start is too far away in the future!!!");
        }
        if(end == null) {
            throw new IllegalArgumentException("End is null!!!");
        }
        if (end.isBefore(start) ) {
            throw new IllegalArgumentException("End must be after start!!!");
        }
        if (end.isAfter(LocalDateTime.MAX)) {
            throw new IllegalArgumentException("End is too far away in the future!!!");
        }

        AbstractTaskList incomingTaskList = TaskListFactory.createTaskList(ListTypes.types.ARRAY);
        Stream<Task> stream = StreamSupport.stream(tasks.spliterator(), false);

        stream.filter(task -> task.nextTimeAfter(start) != null)
                .filter(task -> task.nextTimeAfter(start).isAfter(start))
                .filter(task -> !task.nextTimeAfter(start).isAfter(end))
                .forEach(incomingTaskList::add);

        return incomingTaskList;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(
            Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        if (tasks == null){
            throw new IllegalArgumentException("The list is empty!!!");
        }
        if(start == null) {
            throw new IllegalArgumentException("Start is null!!!");
        }
        if (start.isBefore(LocalDateTime.MIN)) {
            throw new IllegalArgumentException("Start is too far away in the past!!!");
        }
        if (start.isAfter(LocalDateTime.MAX)) {
            throw new IllegalArgumentException("Start is too far away in the future!!!");
        }
        if(end == null) {
            throw new IllegalArgumentException("End is null!!!");
        }
        if (end.isBefore(start) ) {
            throw new IllegalArgumentException("End must be after start!!!");
        }
        if (end.isAfter(LocalDateTime.MAX)) {
            throw new IllegalArgumentException("End is too far away in the future!!!");
        }

        SortedMap<LocalDateTime, Set<Task>> sortedMap = new TreeMap<>();

        for (Task task : Tasks.incoming(tasks, start, end)) {
            for(LocalDateTime nextStart = task.nextTimeAfter(start);
                nextStart != null && !nextStart.isAfter(end);
                nextStart = task.nextTimeAfter(nextStart)){
                if (sortedMap.containsKey(nextStart)) {
                    sortedMap.get(nextStart).add(task);
                } else {
                    Set<Task> set = new HashSet<>();
                    set.add(task);
                    sortedMap.put(nextStart, set);
                }
            }
        }
        return sortedMap;
    }
}
