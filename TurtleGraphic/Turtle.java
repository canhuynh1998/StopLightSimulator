package TurtleGraphic;
import java.awt.Color;
import java.util.*;
import java.util.List;
import tools.*;
public class Turtle extends Bean {
    private Point currentPosition;
    private List<Point> path;
    private Color color;
    private Heading headTo;
    private boolean penIsDown;

    public Turtle(){
        currentPosition = new Point(5,5);
        path = new LinkedList<Point>();
        penIsDown = true;
        color = Color.BLACK;
    }
    public void setPenIsDown() {
        //Set pen to true if false else true
        color = penIsDown ? Color.BLACK : Color.WHITE;
        penIsDown = !penIsDown;
    }

    public void setColor(Color color){
        this.color=color;
    }

    public Color getColor() { return color; }

    public void turn(Heading direction){ headTo = direction; }  //Set the moving direction of the turtle

    public void move(int steps, int lowerBound, int rightBound){
        //Update the path list
        //This list will only store Point when penIsDown == true
        if(headTo == Heading.SOUTH){
            if(penIsDown) {
                moveSouth(steps, lowerBound);
            }
        }else if(headTo == Heading.NORTH){
            if(penIsDown) {
                moveNorth(steps, lowerBound);
            }
        } else if (headTo == Heading.EAST) {
            if(penIsDown) {
              moveEast(steps, rightBound);
            }
        }else if(headTo == Heading.WEST){
            if(penIsDown) {
                moveWest(steps, rightBound);
            }
        }

        Point lastPoint = path.get(path.size()-1);
        currentPosition.setxCoord(lastPoint.getxCoord());
        currentPosition.setyCoord(lastPoint.getyCoord());
    }

    private void moveNorth(int yCoord, int outOfBound){
        //Moving upward
        int start = 0;
        int newYCoord = currentPosition.getyCoord() - 1;
        int newXCoord = currentPosition.getxCoord();
        while(start < yCoord){
            if(newYCoord < 0){
                newYCoord += outOfBound;
            }
            Point newPoint = new Point(newXCoord, newYCoord, this.color);
            newYCoord -= 1;
            path.add(newPoint);
            start++;
        }
    }

    private void moveSouth(int yCoord, int outOfBound){
        //Moving downward
        int start = 0;
        int newYCoord = currentPosition.getyCoord() + 1;
        int newXCoord = currentPosition.getxCoord();
        while(start < yCoord){
            if(newYCoord > outOfBound){
                newYCoord -= outOfBound;
            }
            Point newPoint = new Point(newXCoord, newYCoord, this.color);
            newYCoord ++;
            path.add(newPoint);
            start++;
        }
    }

    private void moveEast(int xCoord, int outOfBound){
        //Moving to the right
        int start = 0;
        int newYCoord = currentPosition.getyCoord();
        int newXCoord = currentPosition.getxCoord() + 1;
        while(start < xCoord){
            if(newXCoord > outOfBound){
                newXCoord -= outOfBound;
            }
            Point newPoint = new Point(newXCoord, newYCoord, this.color);
            newXCoord ++;
            path.add(newPoint);
            start++;
        }
    }

    private void moveWest(int xCoord, int outOfBound){
        //Moving to the left
        int start = 0;
        int newYCoord = currentPosition.getyCoord();
        int newXCoord = currentPosition.getxCoord() - 1;
        while(start < xCoord){
            if(newXCoord < 0){
                newXCoord += outOfBound;
            }
            Point newPoint = new Point(newXCoord, newYCoord, this.color);
            newXCoord --;
            path.add(newPoint);
            start++;
        }
    }

    public static void main(String[] args){
        Turtle turtle = new Turtle();
        turtle.turn(Heading.NORTH);
        System.out.println(turtle.headTo);
        turtle.move(10, 12, 12);
        System.out.println(turtle.currentPosition.getxCoord());
        System.out.println(turtle.currentPosition.getyCoord());
        System.out.println();
        turtle.turn(Heading.SOUTH);
        turtle.move(1, 12, 12);
        System.out.println(turtle.currentPosition.getxCoord());
        System.out.println(turtle.currentPosition.getyCoord());
        System.out.println();
        turtle.turn(Heading.EAST);
        turtle.move(10, 12, 12);
        System.out.println(turtle.currentPosition.getxCoord());
        System.out.println(turtle.currentPosition.getyCoord());
        System.out.println();
        turtle.turn(Heading.WEST);
        turtle.move(18, 12, 12);
        System.out.println(turtle.currentPosition.getxCoord());
        System.out.println(turtle.currentPosition.getyCoord());
    }
}
