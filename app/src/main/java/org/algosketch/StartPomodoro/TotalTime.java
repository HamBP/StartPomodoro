package org.algosketch.StartPomodoro;

public class TotalTime {
    private int _hour;
    private int _minute;

    public void minuteTohours(int minute) {
        _hour = minute / 60;
        _minute = minute % 60;
    }

    public int getHour() {
        return _hour;
    }

    public int getMinute() {
        return _minute;
    }
}
