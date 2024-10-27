package com.jt.fpltracker.months;

import lombok.Value;

@Value
public class Month {
    int id;
    String name;
    int start_event;
    int stop_event;
}
