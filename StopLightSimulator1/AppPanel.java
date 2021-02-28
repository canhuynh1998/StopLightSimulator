package StopLightSimulator1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class AppPanel extends JPanel implements ActionListener {
    private JFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;
    public AppPanel(){
        JPanel controlJPanel = new JPanel();
        JPanel controlJPanel1 = new JPanel();
        setLayout((new GridLayout(1, 2)));
        add(controlJPanel1);
        add(controlJPanel);

        //controlJPanel.setBackground(Color.CYAN);
        controlJPanel.setBackground(Color.WHITE);

        frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Stoplight Simulator");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
	// write your code here
        System.out.println("Test");
        AppPanel app = new AppPanel();
    }
}