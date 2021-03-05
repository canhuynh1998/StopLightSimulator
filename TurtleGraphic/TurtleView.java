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
        System.out.println(previous.getxCoord()+"  "+previous.getyCoord());
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        gc.setColor(model.getColor());
        gc.fillOval(model.getCurrentPosition().getxCoord(), model.getCurrentPosition().getyCoord(), diameter, diameter);
        for(Point current : model.getList()){
            if(current.getEndPoint() == false && previous.getEndPoint() == false){
                gc.drawLine(previous.getxCoord(), previous.getyCoord(), current.getxCoord(), current.getyCoord());
            }

            gc.setColor(oldColor);
            previous = new Point(current.getxCoord(), current.getyCoord(), current.getPos());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0){
        repaint();
    }
}