package TurtleGraphic;
import java.awt.Color;
import java.util.*;
import java.util.List;
import tools.*;
public class Turtle extends Bean {
    public static Integer WORLD_SIZE = 250; // height & width of the world (& view)
    private Point currentPosition;
    private List<Point> path;
    private Color color;
    private Heading headTo;
    private boolean penIsDown;

    public Turtle(){
        currentPosition = new Point(50,50);
        path = new ArrayList<Point>();
        path.add(new Point(currentPosition.getxCoord(), currentPosition.getyCoord()));
        penIsDown = true;
        color = Color.BLACK;
    }
    public void setPenIsDown() {
        //Set pen to true if false else true
        penIsDown = !penIsDown;
        color = penIsDown ? Color.BLACK : Color.BLUE;
        if(penIsDown){
            path.add(currentPosition);
        }else{
            currentPosition.setEndPoint(true);
        }
        firePropertyChange(null, null, null);
    }

    public boolean getPenIsDown(){ return penIsDown;}

    public Point getCurrentPosition(){return this.currentPosition;}

    public Color getColor() { return color; }

    public void setColor(Color color){this.color = color;}
    public void turn(Heading direction){ headTo = direction; }  //Set the moving direction of the turtle

    public List<Point> getList(){ return path; }

    public void move(int steps){
        if(headTo == Heading.SOUTH){
            moveSouth(steps);
        }else if(headTo == Heading.NORTH){
            moveNorth(steps);
        }else if(headTo == Heading.EAST){
            moveEast(steps);
        }else if(headTo == Heading.WEST) {
            moveWest(steps);
        }
        firePropertyChange(null, null, null);
    }

    public void clearPath(){
        //Clear the whole path.
        Point temp = currentPosition;
        path.clear();
        path.add(temp);
        firePropertyChange(null, null, null);
    }
    public Point getCur(){
        return currentPosition;
    }
    private void moveNorth(int yCoord){
        //Moving upward
        Point newPoint = null;
        if(penIsDown){
            if(currentPosition.getyCoord() - yCoord <= 0){
                Point pointToBound = new Point(currentPosition.getxCoord(), 0, currentPosition.getColor());
                path.add(pointToBound);

                //flag1, flag2 are using for bound checking when drawing
                Point flag1 = new Point(currentPosition.getxCoord(), -1);
                path.add(flag1);
                Point flag2 = new Point(currentPosition.getxCoord(), WORLD_SIZE+1);
                path.add(flag2);

                Point pointFromBound = new Point(currentPosition.getxCoord(),
                        WORLD_SIZE, currentPosition.getColor());
                path.add(pointFromBound);
                newPoint = new Point(currentPosition.getxCoord(),
                        currentPosition.getyCoord() - yCoord + WORLD_SIZE, currentPosition.getColor());
            }else {
                newPoint = new Point(currentPosition.getxCoord(), currentPosition.getyCoord() - yCoord, currentPosition.getColor());
            }
            path.add(newPoint);
            currentPosition = new Point(newPoint.getxCoord(), newPoint.getyCoord(), currentPosition.getColor());
        }else{
            int newYpos = currentPosition.getyCoord() - yCoord < 0 ?
                    currentPosition.getyCoord() - yCoord + WORLD_SIZE: currentPosition.getyCoord() - yCoord;
            int newXpos = currentPosition.getxCoord();
            Color newColor = currentPosition.getColor();
            newPoint = new Point(newXpos, newYpos);
            currentPosition = new Point(newPoint.getxCoord(), newPoint.getyCoord(), newColor);
            currentPosition.setEndPoint(true);
        }
    }

    private void moveSouth(int yCoord){
        //Moving downward
        if(penIsDown){
            Point newPoint = null;
            if(currentPosition.getyCoord() + yCoord >= WORLD_SIZE){
                Point pointToBound = new Point(currentPosition.getxCoord(), WORLD_SIZE, currentPosition.getColor());
                path.add(pointToBound);

                //flag1, flag2 are using for bound checking when drawing
                Point flag2 = new Point(currentPosition.getxCoord(), WORLD_SIZE+1);
                path.add(flag2);
                Point flag1 = new Point(currentPosition.getxCoord(), -1);
                path.add(flag1);

                Point pointFromBound = new Point(currentPosition.getxCoord(),
                        0, currentPosition.getColor());
                path.add(pointFromBound);
                newPoint = new Point(currentPosition.getxCoord(),
                        currentPosition.getyCoord() + yCoord - WORLD_SIZE, currentPosition.getColor());
            }else {
                newPoint = new Point(currentPosition.getxCoord(), currentPosition.getyCoord() + yCoord, currentPosition.getColor());
            }
            path.add(newPoint);
            currentPosition = new Point(newPoint.getxCoord(), newPoint.getyCoord(), currentPosition.getColor());
        }else{
            int newYpos = currentPosition.getyCoord() + yCoord > WORLD_SIZE ?
                    currentPosition.getyCoord() + yCoord - WORLD_SIZE: currentPosition.getyCoord() + yCoord;
            int newXpos = currentPosition.getxCoord();
            Color newColor = currentPosition.getColor();
            currentPosition = new Point(newXpos, newYpos, newColor);
            currentPosition.setEndPoint(true);
        }
    }

    private void moveEast(int xCoord){
        //Moving to the right
        Point newPoint = null;
        if(penIsDown){
            if(currentPosition.getxCoord() + xCoord >= WORLD_SIZE){
                Point pointToBound = new Point(WORLD_SIZE, currentPosition.getyCoord(), currentPosition.getColor());
                path.add(pointToBound);

                //flag1, flag2 are using for bound checking when drawing
                Point flag2 = new Point(WORLD_SIZE+1,currentPosition.getyCoord());
                path.add(flag2);
                Point flag1 = new Point(-1, currentPosition.getyCoord());
                path.add(flag1);

                Point pointFromBound = new Point(0,
                        currentPosition.getyCoord(), currentPosition.getColor());
                path.add(pointFromBound);
                newPoint = new Point( currentPosition.getxCoord() + xCoord - WORLD_SIZE,
                        currentPosition.getyCoord());
            }else {
                newPoint = new Point(currentPosition.getxCoord() + xCoord, currentPosition.getyCoord(), currentPosition.getColor());
            }
            path.add(newPoint);
            currentPosition = new Point(newPoint.getxCoord(), newPoint.getyCoord(), currentPosition.getColor());
        }else{
            int newYpos = currentPosition.getyCoord() ;
            int newXpos = currentPosition.getxCoord() + xCoord > WORLD_SIZE ?
                    currentPosition.getxCoord() + xCoord - WORLD_SIZE: currentPosition.getxCoord() + xCoord;
            newPoint = new Point(newXpos, newYpos);
            System.out.println("Test: "+newPoint.getxCoord()+" "+newPoint.getyCoord()+")");
            Color newColor = currentPosition.getColor();
            currentPosition = new Point(newPoint.getxCoord(), newPoint.getyCoord(), newColor);
            currentPosition.setEndPoint(true);
        }
    }

    private void moveWest(int xCoord){
        //Moving to the left
        Point newPoint = null;
        if(penIsDown){
            if(currentPosition.getxCoord() - xCoord <= 0){
                Point pointToBound = new Point(0, currentPosition.getyCoord(), currentPosition.getColor());
                path.add(pointToBound);

                //flag1, flag2 are using for bound checking when drawing
                Point flag1 = new Point(-1, currentPosition.getyCoord());
                path.add(flag1);
                Point flag2 = new Point(WORLD_SIZE+1,currentPosition.getyCoord() );
                path.add(flag2);

                Point pointFromBound = new Point(WORLD_SIZE,
                        currentPosition.getyCoord());
                path.add(pointFromBound);
                newPoint = new Point( currentPosition.getxCoord() - xCoord + WORLD_SIZE,
                        currentPosition.getyCoord(), currentPosition.getColor());
            }else {
                newPoint = new Point(currentPosition.getxCoord() - xCoord, currentPosition.getyCoord(), currentPosition.getColor());
            }
            path.add(newPoint);
            currentPosition = new Point(newPoint.getxCoord(), newPoint.getyCoord(), currentPosition.getColor());
        }else{
            int newYpos = currentPosition.getyCoord() ;
            int newXpos = currentPosition.getxCoord() - xCoord < 0 ?
                    currentPosition.getxCoord() - xCoord + WORLD_SIZE: currentPosition.getxCoord() - xCoord;
            newPoint = new Point(newXpos, newYpos);
            Color newColor = currentPosition.getColor();
            currentPosition = new Point(newPoint.getxCoord(), newPoint.getyCoord(), newColor);
            currentPosition.setEndPoint(true);
        }

    }

    public static void main(String[] args){
        Turtle turtle = new Turtle();
        System.out.println(turtle.getList().size());
        turtle.turn(Heading.NORTH);
        //System.out.println(turtle.headTo);
        turtle.move(10);
//        for(Point point: turtle.path){
//            System.out.println(point.getxCoord()+" "+point.getyCoord());
//        }

//        System.out.println(turtle.currentPosition.getyCoord());
//        System.out.println();
//        turtle.turn(Heading.SOUTH);
//        for(Point point: turtle.path){
//            System.out.println(point.getxCoord()+" "+point.getyCoord());
//        }
//        turtle.move1(1);
//        System.out.println(turtle.currentPosition.getxCoord());
//        System.out.println(turtle.currentPosition.getyCoord());
//        System.out.println();
        turtle.turn(Heading.EAST);
        turtle.move(10);
//        for(Point point: turtle.path){
//            System.out.println(point.getxCoord()+" "+point.getyCoord());
//        }
//        System.out.println(turtle.currentPosition.getxCoord());
//        System.out.println(turtle.currentPosition.getyCoord());
//        System.out.println();
//        turtle.turn(Heading.WEST);
//        turtle.move1(18);
//        System.out.println(turtle.currentPosition.getxCoord());
//        System.out.println(turtle.currentPosition.getyCoord());
//        System.out.println();
        System.out.println(turtle.getList().size());
    }
}
