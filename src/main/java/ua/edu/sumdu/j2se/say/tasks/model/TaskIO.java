package ua.edu.sumdu.j2se.say.tasks.model;


import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * Class TaskIO.
 * This class contains methods for reading and writing to a file.
 * @author Dmytro Say
 */
public class TaskIO {
    private static final Logger log = LogManager.getLogger(TaskIO.class);

    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(out))) {
            dos.writeInt(tasks.size());
            for (Task t : tasks) {
                dos.writeInt(t.getTitle().length());
                dos.writeUTF(t.getTitle());
                if (t.isActive()) {
                    dos.writeInt(1);
                }
                else {
                    dos.writeInt(0);
                }
                dos.writeInt(t.getRepeatInterval());
                if (t.isRepeated()) {
                    dos.writeLong(t.getStartTime().toEpochSecond(ZoneOffset.UTC));
                    dos.writeLong(t.getEndTime().toEpochSecond(ZoneOffset.UTC));
                }
                else {
                    dos.writeLong(t.getStartTime().toEpochSecond(ZoneOffset.UTC));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) {
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(in))) {
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                int titleLength = dis.readInt();
                String title = dis.readUTF();
                boolean active = (dis.readInt() == 1);
                int interval = dis.readInt();

                Task task;
                if (interval > 0) {
                    task = new Task(title,
                            LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC),
                            LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC),
                            interval);
                } else {
                    task = new Task(title,
                            LocalDateTime.ofEpochSecond(dis.readLong(), 0, ZoneOffset.UTC));
                }
                task.setActive(active);
                tasks.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) {
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            write(tasks, bufferedOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            read(tasks, bufferedInputStream);
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

                    jsonWriter.name("Time").value(task.getStartTime().toString());
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
            Task taskToRead;
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
                    taskToRead = new Task(title, start, end, interval);
                } else {
                    taskToRead = new Task(title, time);
                }
                taskToRead.setActive(active);
                tasks.add(taskToRead);
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