package se.nackademin;

import java.io.Serializable;

/**
 * Square class for keeping track of marks on its location
 */
public class Square implements Serializable {
    String mark = " ";

    public Square() {
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }


}
