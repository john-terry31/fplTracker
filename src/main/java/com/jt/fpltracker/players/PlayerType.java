package com.jt.fpltracker.players;

import lombok.Value;

import java.util.List;

@Value
public class PlayerType {
    int id;
    String pluralName;
    String pluralNameShort;
    String name;
    String shortName;
    int squadSelect;
    int squadMinPlay;
    int squadMaxPlay;
    boolean uiShirtSpecific;
    List<Integer> subPositionsLocked;
    int elementCount;
}
