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
        path.add(new Point(50, 50));
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
    public Point getCurrentPosition(){return this.currentPosition;}

    public void setColor(Color color){
        this.color=color;
    }
    public Color getColor() { return color; }

    public void turn(Heading direction){ headTo = direction; }  //Set the moving direction of the turtle

    public List<Point> getList(){ return path; }

    public void move(int steps){
        int newXpos = currentPosition.getxCoord();
        int newYpos = currentPosition.getyCoord();
        if(headTo == Heading.SOUTH){
            newYpos = newYpos + steps > WORLD_SIZE ?
                    newYpos + steps - WORLD_SIZE: newYpos +steps;
        }else if(headTo == Heading.NORTH){
//            newYpos = newYpos - steps < 0 ?
//                    newYpos - steps + WORLD_SIZE: newYpos - steps;
            moveNorth(steps);
            for(Point test: path){
                System.out.print("("+""+test.getxCoord()+" "+test.getyCoord()+") position at: ");
            }
            System.out.println();
        }else if(headTo == Heading.EAST){
            newXpos =  newXpos + steps > WORLD_SIZE ?
                    newXpos + steps - WORLD_SIZE : newXpos + steps;
        }else if(headTo == Heading.WEST) {
            newXpos = newXpos - steps < 0 ? newXpos - steps + WORLD_SIZE : newXpos - steps;
        }
//        Point newPoint = new Point(newXpos, newYpos);
//        System.out.println("\n/********************Before*****************/");
//        for(Point test: path){
//            System.out.print("("+""+test.getxCoord()+" "+test.getyCoord()+") position at: "+test.getPos()+"; ");
//        }
//        System.out.println();
//
//        System.out.println();
//        if(penIsDown){
//            path.add(newPoint);
//        }else{
//            newPoint.setEndPoint(true);
//        }
//        currentPosition = new Point(newXpos, newYpos);
//        currentPosition.setEndPoint(newPoint.getEndPoint());
//        System.out.print("("+""+currentPosition.getxCoord()+" "+currentPosition.getyCoord()+") position at: "+currentPosition.getEndPoint()+"; ");
//        System.out.println("\n/********************After*****************/");
//        for(Point test: path){
//            System.out.print("("+""+test.getxCoord()+" "+test.getyCoord()+") position at: "+test.getPos()+"; ");
//        }
        firePropertyChange(null, null, null);
    }

    private void moveNorth(int yCoord){
        //Moving upward
        if(penIsDown){
            if(currentPosition.getyCoord() - yCoord <= 0){
                Point pointToBound = new Point(currentPosition.getxCoord(), 0);
                path.add(pointToBound);

                //flag1, flag2 are using for bound checking when drawing
                Point flag1 = new Point(currentPosition.getxCoord(), -1);
                path.add(flag1);
                Point flag2 = new Point(currentPosition.getxCoord(), WORLD_SIZE+1);
                path.add(flag2);

                Point pointFromBound = new Point(currentPosition.getxCoord(),
                        WORLD_SIZE);
                path.add(pointFromBound);
                Point newPoint = new Point(currentPosition.getxCoord(),
                        currentPosition.getyCoord() - yCoord + WORLD_SIZE);
                path.add(newPoint);
                currentPosition = new Point(newPoint.getxCoord(), newPoint.getyCoord());
            }else {
                Point newPoint = new Point(currentPosition.getxCoord(), currentPosition.getyCoord() - yCoord);
                path.add(newPoint);
                currentPosition = new Point(newPoint.getxCoord(), newPoint.getyCoord());
            }
        }else{
            int newYpos = currentPosition.getyCoord() - yCoord < 0 ?
                    currentPosition.getyCoord() - yCoord + WORLD_SIZE: currentPosition.getyCoord() - yCoord;
            int newXpos = currentPosition.getxCoord();
            currentPosition = new Point(newXpos, newYpos);
            currentPosition.setEndPoint(true);
        }
    }

    private void moveSouth(int yCoord){
        //Moving downward
        int start = 0;
        int newYCoord = currentPosition.getyCoord() + 1;
        int newXCoord = currentPosition.getxCoord();
        while(start < yCoord){
            if(newYCoord > WORLD_SIZE){
                newYCoord -= WORLD_SIZE;
            }
            Point newPoint = new Point(newXCoord, newYCoord, this.color);
            newYCoord ++;
            path.add(newPoint);
            start++;
        }
    }

    private void moveEast(int xCoord){
        //Moving to the right
        int start = 0;
        int newYCoord = currentPosition.getyCoord();
        int newXCoord = currentPosition.getxCoord() + 1;
        while(start < xCoord){
            if(newXCoord > WORLD_SIZE){
                newXCoord -= WORLD_SIZE;
            }
            Point newPoint = new Point(newXCoord, newYCoord, this.color);
            newXCoord ++;
            path.add(newPoint);
            start++;
        }
    }

    private void moveWest(int xCoord){
        //Moving to the left
        int start = 0;
        int newYCoord = currentPosition.getyCoord();
        int newXCoord = currentPosition.getxCoord() - 1;
        while(start < xCoord){
            if(newXCoord < 0){
                newXCoord += WORLD_SIZE;
            }
            Point newPoint = new Point(newXCoord, newYCoord, this.color);
            newXCoord --;
            path.add(newPoint);
            start++;
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
