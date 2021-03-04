package TurtleGraphic;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import tools.*;

public class TurtlePanel extends JPanel implements ActionListener {
    private Turtle model;
    private TurtleView view;

    private JFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;
    public TurtlePanel(){
        model = new Turtle();
        view = new TurtleView(model);
        JPanel controlJPanel1 = new JPanel();
        setLayout((new GridLayout(1, 2)));
        add(controlJPanel1);
        add(view);

        controlJPanel1.setBackground(Color.CYAN);
        view.setBackground(Color.WHITE);

        controlJPanel1.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));
        JButton NorthButton = new JButton("North");
        NorthButton.addActionListener(this);
        controlJPanel1.add(NorthButton);

        JButton SouthButton = new JButton("South");
        SouthButton.addActionListener(this);
        controlJPanel1.add(SouthButton);

        JButton EastButton = new JButton("East");
        EastButton.addActionListener(this);
        controlJPanel1.add(EastButton);

        JButton WestButton = new JButton("West");
        WestButton.addActionListener(this);
        controlJPanel1.add(WestButton);

        JButton DrawButton = new JButton("Pen");
        DrawButton.addActionListener(this);
        controlJPanel1.add(DrawButton);

        frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Turtle Graphic");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar(){
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        // this keyword means TurtlePanel
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu =
                Utilities.makeMenu("Edit",
                        new String[] {"North", "South", "East", "West", "Pen", "Color"}, this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    public void actionPerformed(ActionEvent ae) {
        String cmnd = ae.getActionCommand();
        if(cmnd == "About"){
            Utilities.inform("Cyberdellic Designs Stoplight Simulator 1.1, 2020. All rights reserved.");
        }else if(cmnd == "Help" ){
            Utilities.inform("Click or select direction buttons to draw at the desired direction");
        }else if(cmnd == "Pen"){
          model.setPenIsDown();
          System.out.println(model.getColor());
        } else if(cmnd == "North"){
            int steps = Integer.parseInt(Utilities.ask("How many steps"));
            model.turn(Heading.NORTH);
            model.move(steps);
            System.out.println("North");
        } else if(cmnd == "East"){
            int steps = Integer.parseInt(Utilities.ask("How many steps"));
            model.turn(Heading.EAST);
            model.move(steps);
            System.out.println("East");
        }
    }


    public static void main(String[] args) {
	// write your code here
        System.out.println("Test");
        TurtlePanel app = new TurtlePanel();
    }
}
