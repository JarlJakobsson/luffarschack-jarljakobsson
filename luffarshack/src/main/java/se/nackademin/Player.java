package se.nackademin;

import java.io.Serializable;

public class Player implements Serializable{
    private String name;
    private String mark;

    public Player(String name, String mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMarker(String marker) {
        this.mark = marker;
    }
}
