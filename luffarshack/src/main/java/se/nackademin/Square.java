package se.nackademin;

/**
 * Square class for keeping track of bricks on its location
 */
public class Square {
    String mark = " ";
    String cursorMark = "( )"; 

    public Square() {
    }


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getCursorMark() {
        return cursorMark;
    }

    public void setCursorMark(String mark) {
        this.cursorMark = "{" + mark + "}";
    }

}
