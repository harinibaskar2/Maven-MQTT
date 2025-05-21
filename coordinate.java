
 
/**
 * 
 * @author hbaskar
 * @version 1.1
 * 
 */


import java.io.Serializable;

public class coordinate implements Serializable {

    private int mouseX;
    private int mouseY;

    public coordinate(int mouseX, int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public coordinate() {
        this(0, 0);
    }


    public int getX() {
        return mouseX;
    }

    public int getY() {
        return mouseY;
    }


    public void setBallX(int mouseX) {this.mouseX = mouseX;}

    public void setBallY(int mouseX) {this.mouseY = mouseY;}


}