package com.jt.fpltracker.teams;

import lombok.Value;

@Value
public class Team {
    int code;
    int draw;
    Integer form;
    int id;
    int loss;
    String name;
    int played;
    int points;
    int position;
    String shortName;
    int strength;
    Integer teamDivision;
    boolean unavailable;
    int win;
    int strengthOverallHome;
    int strengthOverallAway;
    int strengthAttackHome;
    int strengthAttackAway;
    int strengthDefenceHome;
    int strengthDefenceAway;
    int pulse_id;
}
