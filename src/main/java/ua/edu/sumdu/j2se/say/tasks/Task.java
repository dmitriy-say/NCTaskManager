package ua.edu.sumdu.j2se.say.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Клас об'єктів "Задача".
 * Клас, який описує об'єкти "Задача".
 * 
 * @author Say Dmytro.
 * @version 1.0.
 */
public class Task implements Cloneable {
    /**
     * Назва задачі.
     * Містить деякий текст, який описує деталі задачі,
     * наприклад "Прибирання в кімнаті".
     */
    private String title;
    /**
     * Стан задачі.
     * Задачі можуть бути активними та неактивними – наприклад,
     * на час відпустки задача "Ранкова пробіжка" може бути
     * неактивною і тимчасово не виконуватись.
     * Якщо active = true - задача активна, якщо false - неактивна.
     */
    private boolean active;
    /**
     * Повторюваність задачі.
     * Задачі можуть бути заплановані на виконання один раз,
     * наприклад "Зустріч у кафе 26 червня о 18:00". Або задача
     * може бути запланована на регулярне виконання, наприклад,
     * "Ранкова пробіжка з 1 червня по 5 червня кожну добу о 8:00".
     * Якщо repeated = true - задача запланована на регулярне виконання,
     * якщо false - на один раз.
     */
    private boolean repeated;
    /**
     * Час, на який заплановане виконання одноразової задачі.
     */
    private LocalDateTime time;
    /**
     * Час початку виконання регулярної задачі.
     */
    private LocalDateTime start;
    /**
     * Час кінця виконання регулярної задачі.
     */
    private LocalDateTime end;
    /**
     * Інтервал виконання періодичної задачі.
     * Якщо задача запланована на регулярне виконання, то інтервал
     * показує задану періодичність виконання задачі (у годинах).
     */
    private int interval;

    /**
     * Конструктор, що конструює неактивну задачу,
     * яка виконується у заданий час без повторення із заданою назвою.
     * 
     * @param title - назва задачі;
     * @param time  - час, на який заплановане виконання задачі.
     */
    public Task(String title, LocalDateTime time) {
        if (title == null) {
            throw new IllegalArgumentException("It doesn't make sense to create an untitled task!");
        } else if (time == null){
            throw new IllegalArgumentException("Time cannot be null!");
        } else if (time.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Time cannot be earlier than current!");
        } else {
            this.title = title;
            this.time = time;
            this.active = false;
            this.repeated = false;
        }
    }
    /**
     * Конструктор, що конструює неактивну задачу,
     * яка виконується у заданому проміжку часу
     * (і початок, і кінець включно) із заданим інтервалом
     * і має задану назву.
     * 
     * @param title    - назва задачі;
     * @param start    - час початку задачі;
     * @param end      - час кінця задачі;
     * @param interval - інтервал, з яким повторюється задача.
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        if (title == null) {
            throw new IllegalArgumentException("It doesn't make sense to create an untitled task!");
        } else if (start == null || end == null) {
            throw new IllegalArgumentException("Time cannot be null!");
        } else if (start.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Time is in the past!");
        } else if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time must be longer than start time!");
        } else if (interval <= 0) {
            throw new IllegalArgumentException("The repetition interval of the task must be greater than zero!");
        } else {
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.active = false;
            this.repeated = true;
        }
    }
    /**
     * Метод для зчитування назви задачі.
     * 
     * @return - title (назву задачі).
     */
    public String getTitle() {
        return title;
    }
    /**
     * Метод для встановлення назви задачі.
     * 
     * @param title назва задачі.
     */
    public void setTitle(String title) {
        if (title == null) {
            throw new NullPointerException("It doesn't make sense to create an untitled task!");
        } else {
            this.title = title;
        }
    }
    /**
     * Метод для зчитування стану задачі.
     * 
     * @return - active (стан задачі: true - активна, false - неактивна).
     */
    public boolean isActive() {
        return active;
    }
    /**
     * Метод для встановлення стану задачі.
     * 
     * @param active - (стан задачі: true - активна, false - неактивна).
     */
    public void setActive(boolean active) {
        this.active = active;
    }
    /**
     * Метод для зчитування часу виконання для задач, що не повторюються.
     * 
     * @return якщо задача не повторюється повертає час початку виконання
     *         задачі. Якщо задача повторюється повертає час початку повторення
     *         задачі.
     */
    public LocalDateTime getTime() {
        if (repeated) {
            return start;
        } else {
            return time;
        }
    }
    /**
     * Метод для зміни часу виконання для задач, що не повторюються.
     * У разі, якщо задача повторювалась, вона стає такою, що не повторюється.
     * 
     * @param time час початку виконання задачі.
     */
    public void setTime(LocalDateTime time) {
        if (time.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Time cannot be earlier than current!");
        } else {
            this.time = time;
            if (repeated) {
                repeated = false;
            }
        }
    }
    /**
     * Метод для зчитування часу початку виконання
     * для задач, що повторюються.
     * 
     * @return - якщо задача повторюється,
     *         повертає час початку повторення задачі.
     *         Якщо задача не повторюється, повертає
     *         час початку виконання задачі.
     */
    public LocalDateTime getStartTime() {
        return repeated ? start : time;
    }
    /**
     * Метод для зчитування часу закінчення виконання
     * для задач, що повторюються.
     * 
     * @return - якщо задача повторюється, повертає
     *         час закінчення виконання задачі. Якщо задача
     *         не повторюється, повертає час початку виконання задачі.
     */
    public LocalDateTime getEndTime() {
        return repeated ? end : time;
    }
    /**
     * Метод для зчитування інтервалу для задач, що повторюються.
     * 
     * @return - якщо задача повторюється повертає інтервал,
     *         якщо задача не повторюється, повертає 0.
     */
    public int getRepeatInterval() {
        return repeated ? interval : 0;
    }
    /**
     * Метод для зміни часу початку, часу закінчення та інтервалу для
     * задач, що повторюються. У разі, якщо задача не повторювалася, то
     * при встановленні часу її початку, закінчення та інтервалу задача
     * стає такою, що повторюється.
     * 
     * @param start    - час початку виконання задачі.
     * @param end      - час закінчення виконання задачі.
     * @param interval - інтервал, з яким повторюється задача.
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        if (start.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Time cannot be negative!");
        } else if (end.isBefore(start)) {
            throw new IllegalArgumentException("End time must be longer than start time!");
        } else if (interval <= 0) {
            throw new IllegalArgumentException("The repetition interval of the task must be greater than zero!");
        } else {
            this.start = start;
            this.end = end;
            this.interval = interval;
            repeated = true;
        }
    }
    /**
     * Метод для перевірки повторюваності задачі.
     * 
     * @return - true - задача повторюється.
     */
    public boolean isRepeated() {
        return this.repeated;
    }
    /**
     * Метод, що повертає час наступного виконання задачі
     * після вказаного часу current. Якщо після вказаного часу
     * задача не виконується, то метод повертає -1.
     * 
     * @param current - вказаний поточний час.
     * @return - наступний час виконання задачі.
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (current.isBefore(LocalDateTime.MIN)) {
            throw new IllegalArgumentException("Time must be positive");
        }
        if (!active) {
            return null;
        }
        if (!repeated) {
            return time.isAfter(current) ? time : null;
        }
        if (current.isBefore(start)) {
            return start;
        }
        if (current.isAfter(end)) {
            return null;
        }
        for (LocalDateTime i = start; i.isBefore(end); i=i.plusHours(interval)) {
            if (i.isAfter(current) && i.isBefore(end)){
                return i;
            }
        }
        return null;
    }
    public Task clone () throws CloneNotSupportedException {
        return (Task) super.clone();
    }
    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", active=" + active +
                ", repeated=" + repeated +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                '}';
    }
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        return Objects.equals(title, ((Task) other).title)
                && active == ((Task) other).active
                && repeated == ((Task) other).repeated
                && time == ((Task) other).time
                && start == ((Task) other).start
                && end == ((Task) other).end
                && interval == ((Task) other).interval;
    }
    @Override
    public int hashCode() {
        int result = title == null ? 0 : title.hashCode();
        result += !active ? 0 : 1;
        result += !repeated ? 0 : 1;
        result = 31 * result + time.hashCode() + start.hashCode() + end.hashCode() + interval;
        return result;
    }
}
