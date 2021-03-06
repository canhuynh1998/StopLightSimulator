package TurtleGraphic;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TurtleView extends JPanel implements PropertyChangeListener {
    private Turtle model;
    private final int diameter;
    public TurtleView(Turtle model){
        this.model = model;
        model.addPropertyChangeListener(this);
        this.diameter = 5;

    }
    public void setModel(Turtle model){
        this.model.removePropertyChangeListener(this);
        this.model = model;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        repaint();
    }

    public void paintComponent(Graphics gc) {
        Point previous = model.getList().get(0);
        super.paintComponent(gc);
        if(model.getPenIsDown()){
            gc.fillOval(model.getCurrentPosition().getxCoord(), model.getCurrentPosition().getyCoord(), diameter, diameter);
        }else{
            gc.drawOval(model.getCurrentPosition().getxCoord(), model.getCurrentPosition().getyCoord(), diameter, diameter);
        }

        for(Point current : model.getList()){
            if(!current.getEndPoint() && !previous.getEndPoint()){
                gc.setColor(current.getColor());
                if(previous.getyCoord() == -1 || previous.getyCoord() == Turtle.WORLD_SIZE +1){
                    previous = new Point(current.getxCoord(), current.getyCoord());
                    continue;
                }
                if(previous.getxCoord() == -1 || previous.getxCoord() == Turtle.WORLD_SIZE +1){
                    previous = new Point(current.getxCoord(), current.getyCoord());
                    continue;
                }
                gc.drawLine(previous.getxCoord(), previous.getyCoord(), current.getxCoord(), current.getyCoord());
            }
            previous = new Point(current.getxCoord(), current.getyCoord());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0){
        repaint();
    }
}