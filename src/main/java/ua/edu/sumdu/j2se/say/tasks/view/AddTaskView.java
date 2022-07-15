package ua.edu.sumdu.j2se.say.tasks.view;

import ua.edu.sumdu.j2se.say.tasks.controller.Controller;
import ua.edu.sumdu.j2se.say.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.say.tasks.model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class AddTaskView implements View {
    @Override
    public int printInfo(AbstractTaskList taskList) {
        System.out.println("Make your choice (type number and press ENTER)!");
        System.out.println("1 - Add repeated task.");
        System.out.println("2 - Add non-repeated task.");
        System.out.println("3 - Go back.");
        int choice = 0;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            choice = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (choice) {
            case 1:{
                String title = null;
                LocalDateTime time = null;

                System.out.println("Type task's title and press ENTER)!");
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    title = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Type start time and press ENTER)!");
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    time = LocalDateTime.parse(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                taskList.add(new Task(title, time));
            }
            case 2: {
                String title = null;
                LocalDateTime start = null;
                LocalDateTime end = null;
                int interval = 0;

                System.out.println("Type task's title and press ENTER)!");
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    title = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Type start time and press ENTER)!");
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    start = LocalDateTime.parse(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Type end time and press ENTER)!");
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    end = LocalDateTime.parse(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Type interval and press ENTER)!");
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    interval = Integer.parseInt(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                taskList.add(new Task(title, start, end, interval));




            }


        }







        System.out.println("Task was added to the tasks list.");
        return Controller.MAIN_MENU_ACTION;
    }
}
