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
        this.diameter = 10;

    }
    public void setModel(Turtle model){
        this.model.removePropertyChangeListener(this);
        this.model = model;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        repaint();
    }

    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        gc.setColor(model.getColor());
        System.out.println(model.getPoint().getxCoord()+" "+ model.getPoint().getyCoord());
        gc.fillOval(model.getPoint().getxCoord(), model.getPoint().getyCoord(), diameter, diameter);
        gc.setColor(oldColor);
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0){
        repaint();
    }
}
