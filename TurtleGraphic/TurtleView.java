package TurtleGraphic;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
public class TurtleView extends JPanel implements PropertyChangeListener {
    private Turtle model;
    private int upperBound;
    private int rightBound;

    public TurtleView(Turtle model, int upperBound, int rightBound){
        this.model = model;
        model.addPropertyChangeListener(this);
        this.upperBound = upperBound;
        this.rightBound = rightBound;

    }
    public void setModel(Turtle model){
        this.model.removePropertyChangeListener(this);
        this.model = model;
        this.model.initSupport();
        this.model.addPropertyChangeListener(this);
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0){}
}
