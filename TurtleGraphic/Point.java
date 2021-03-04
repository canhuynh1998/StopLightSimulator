package TurtleGraphic;
import java.awt.Color;
import java.io.Serializable;

public class Point implements Serializable {
    private int xCoord;
    private int yCoord;
    private Boolean endPoint;
    private Color color;

    public Point(int x, int y){
        xCoord = x;
        yCoord = y;
        color = Color.BLACK;
        endPoint = false;
    }
    public Point(int x, int y, Color colour){
        xCoord = x;
        yCoord = y;
        color = colour;
        endPoint = false;
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

    public Boolean getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Boolean endPoint) {
        this.endPoint = endPoint;
    }
}
