package TurtleGraphic;
import java.awt.Color;


public class Point {
    private int xCoord;
    private int yCoord;
    private Color color;

    public Point(int x, int y){
        xCoord = x;
        yCoord = y;
        color = Color.WHITE;
    }
    public Point(int x, int y, Color color){
        xCoord = x;
        yCoord = y;
        color = color;
    }
    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
