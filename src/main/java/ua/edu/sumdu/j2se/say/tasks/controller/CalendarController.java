package ua.edu.sumdu.j2se.say.tasks.controller;

import ua.edu.sumdu.j2se.say.tasks.view.View;

public class CalendarController extends Controller {
    protected int actionToExecute = 4;
    public CalendarController(View calendarView, int actionToExecute) {
        super(calendarView, Controller.CALENDAR_ACTION);
    }
}
