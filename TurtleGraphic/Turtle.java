package TurtleGraphic;
import java.awt.Color;
import java.util.*;
import java.util.List;

public class Turtle {
    private Point currentPosition;
    private List<Point> path ;
    private Color color;
    private Heading headTo;
    private boolean penIsDown;

    public Turtle(){
        currentPosition = new Point(0,0);
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

    public Color getColor() {
        return color;
    }

    public void turn(Heading direction){
        //Set the moving direction of the turtle
        headTo = direction;
    }

    public void move(int steps){
        //Update the path list
        //This list will only store Point when penIsDown == true

        int newXCoord = currentPosition.getxCoord();
        int newYCoord = currentPosition.getyCoord();

        if(headTo == Heading.SOUTH){
            if(penIsDown) {
                List<Point> headingSouth = moveNorth(steps);
                path.addAll(headingSouth);
            }
            currentPosition.setxCoord(newXCoord);
            currentPosition.setyCoord(steps);
        }else if(headTo == Heading.NORTH){
            if(penIsDown) {
                List<Point> headingNorth = moveNorth(steps);
                path.addAll(headingNorth);
            }
            currentPosition.setxCoord(newXCoord);
            currentPosition.setyCoord(steps);
        } else if (headTo == Heading.EAST) {
            if(penIsDown) {
                List<Point> headingEast = moveEast(steps);
                path.addAll(headingEast);
            }
            currentPosition.setxCoord(steps);
            currentPosition.setyCoord(newYCoord);
        }else if(headTo == Heading.WEST){
            if(penIsDown) {
                List<Point> headingWest = moveWest(steps);
                path.addAll(headingWest);
            }
            currentPosition.setxCoord(steps);
            currentPosition.setyCoord(newYCoord);
        }
    }

    private List<Point> moveNorth(int yCoord){
        //Create list that store Point to the north direction(going up in the coordinate system)
        //This list will join with the main path list.
        List<Point> tempList = new LinkedList<Point>();
        int start = currentPosition.getyCoord()-1;
        int newXCoord = currentPosition.getxCoord();
        while(start >= yCoord){
            Point newPoint = new Point(newXCoord, start, this.color);
            tempList.add(newPoint);
            start--;
        }
        return tempList;
    }

    private List<Point> moveSouth(int yCoord){
        //Create list that store Point to the south direction(going down in the coordinate system)
        //This list will join with the main path list.
        List<Point> tempList = new LinkedList<Point>();
        int start = currentPosition.getyCoord()+1;
        int newXCoord = currentPosition.getxCoord();
        while(start <= yCoord){
            Point newPoint = new Point(newXCoord, start, this.color);
            tempList.add(newPoint);
            start++;
        }
        return tempList;
    }

    private List<Point> moveEast(int xCoord){
        //Create list that store Point to the east direction(to the right)
        //This list will join with the main path list.
        List<Point> tempList = new LinkedList<Point>();
        int start = currentPosition.getxCoord()+1;
        int newYCoord = currentPosition.getyCoord();
        while(start <= xCoord){
            Point newPoint = new Point(start, newYCoord, this.color);
            tempList.add(newPoint);
            start++;
        }
        return tempList;
    }

    private List<Point> moveWest(int xCoord){
        //Create list that store Point to the west direction(to the left)
        //This list will join with the main path list.
        List<Point> tempList = new LinkedList<Point>();
        int start = currentPosition.getxCoord()-1;
        int newYCoord = currentPosition.getyCoord();
        while(start >= xCoord){
            Point newPoint = new Point(start, newYCoord, this.color);
            tempList.add(newPoint);
            start--;
        }
        return tempList;
    }

}
