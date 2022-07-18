package ua.edu.sumdu.j2se.say.tasks.model;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.TimeZone;

/**
 * Class TaskIO.
 * This class contains methods for reading and writing to a file.
 * @author Dmytro Say
 */
public class TaskIO {

    /**
     * Writes tasks from the list to the stream in the binary format described below.
     * @param tasks data to write
     * @param out stream to record
     */
    public static void write(AbstractTaskList tasks, OutputStream out) {
            try(DataOutputStream dataOutputStream = new DataOutputStream(out)) {
                tasks.getStream().filter(Objects::nonNull).forEach(task -> {
                    try{
                        dataOutputStream.writeInt(tasks.size());
                        String title = task.getTitle();
                        dataOutputStream.writeUTF(title);
                        dataOutputStream.writeBoolean(task.isActive());
                        dataOutputStream.writeBoolean(task.isRepeated());
                        if (task.isRepeated()) {
                        dataOutputStream.writeInt(task.getRepeatInterval());
                        dataOutputStream.writeLong(task.getStartTime().atZone(ZoneId.systemDefault())
                                .toInstant().toEpochMilli());
                        dataOutputStream.writeLong(task.getEndTime().atZone(ZoneId.systemDefault())
                                .toInstant().toEpochMilli());
                    } else {
                        dataOutputStream.writeLong(task.getTime().atZone(ZoneId.systemDefault())
                                .toInstant().toEpochMilli());
                    }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            dataOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * Reads tasks from the stream to this task list.
     * @param tasks what
     * @param in where to

     */
    public static void read(AbstractTaskList tasks, InputStream in) {
            try(DataInputStream dataInputStream = new DataInputStream(in)) {
                int taskCount = dataInputStream.readInt();
                for (int i = 0; i < taskCount; i++) {
                    Task task;
                    String title = dataInputStream.readUTF();
                    boolean isActive = dataInputStream.readBoolean();
                    boolean isRepeated = dataInputStream.readBoolean();

                    if (isRepeated) {
                        int interval = dataInputStream.readInt();
                        LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dataInputStream.readLong()),
                                TimeZone.getDefault().toZoneId());
                        LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dataInputStream.readLong()),
                                TimeZone.getDefault().toZoneId());
                        task = new Task(title, startTime, endTime, interval);
                        task.setActive(isActive);
                    } else {
                        LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochMilli(dataInputStream.readLong()),
                                TimeZone.getDefault().toZoneId());
                        task = new Task(title, time);
                        task.setActive(isActive);
                    }
                    tasks.add(task);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * writes tasks from the list to a file.
     * @param tasks what
     * @param file where to
     */
    public static void writeBinary(AbstractTaskList tasks, File file) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            write(tasks, fileOutputStream);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reads tasks from the file to the task list.
     * @param tasks what
     * @param file from where
     */
    public static void readBinary(AbstractTaskList tasks, File file) {
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            read(tasks, fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) {
        try(JsonWriter jsonWriter = new JsonWriter(out)) {
        jsonWriter.beginArray();
        tasks.forEach(task -> {
            try {
                jsonWriter.beginObject();
                jsonWriter.name("Task Name").value(task.getTitle());
                jsonWriter.name("Active Status").value(task.isActive());
                jsonWriter.name("Repeated").value(task.isRepeated());
                if (task.isRepeated()) {
                    jsonWriter.name("Start time").value(task.getStartTime().toString());
                    jsonWriter.name("End time").value(task.getEndTime().toString());
                    jsonWriter.name("Interval").value(task.getRepeatInterval());
                } else {

                    jsonWriter.name("Time").value(task.getTime().toString());
                }
                jsonWriter.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        jsonWriter.endArray();
        jsonWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the stream in JSON format
     * @param tasks task list
     * @param in where tasks are read
     */
    public static void read(AbstractTaskList tasks, Reader in) {
        try (JsonReader jsonReader = new JsonReader(in)) {
            String title = null;
            Task taskAux;
            String startString, endString, timeString;
            LocalDateTime start = null, end = null, time = null;
            int interval = 0;
            boolean active = false, repeated = false;

            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String name = jsonReader.nextName();
                    switch (name) {
                        case "Task Name" -> title = jsonReader.nextString();
                        case "Active Status" -> active = jsonReader.nextBoolean();
                        case "Repeated" -> repeated = jsonReader.nextBoolean();
                        case "Start time" -> {
                            startString = jsonReader.nextString();
                            start = LocalDateTime.parse(startString);
                        }
                        case "End time" -> {
                            endString = jsonReader.nextString();
                            end = LocalDateTime.parse(endString);
                        }
                        case "Interval" -> interval = jsonReader.nextInt();

                        case "Time" -> {
                            timeString = jsonReader.nextString();
                            time = LocalDateTime.parse(timeString);
                        }
                    }
                }

                if (repeated) {
                    taskAux = new Task(title, start, end, interval);
                } else {
                    taskAux = new Task(title, time);
                }
                taskAux.setActive(active);
                tasks.add(taskAux);
                jsonReader.endObject();
            }
            jsonReader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes tasks to a file in JSON format
     * @param tasks task to be written
     * @param file -
     */
    public static void writeText(AbstractTaskList tasks, File file) {
        try(FileWriter fileWriter = new FileWriter(file)) {
            write(tasks, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads tasks from the file (JSON format)
     * @param tasks list where the the tasks will be saved
     * @param file -
     */
    public static void readText(AbstractTaskList tasks, File file) {
        try(FileReader fileReader = new FileReader(file)) {
            read(tasks, fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}