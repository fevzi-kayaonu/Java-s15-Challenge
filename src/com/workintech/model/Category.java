package com.workintech.model;

public enum Category {
    JOURNAL(1),
    STUDY(2),
    SCIENCE_FICTION(3),
    FANTASY(4),
    ROMANCE(5),
    HISTORY(6),
    CLASSIC(7);

    private final int value;

    Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

