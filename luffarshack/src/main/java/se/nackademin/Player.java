package se.nackademin;

public class Player {
    private String name;
    private String mark = "X";

    public Player() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void placeMarker() {
        
    }

    public String getMark() {
        return mark;
    }

    public void setMarker(String marker) {
        this.mark = marker;
    }
}
