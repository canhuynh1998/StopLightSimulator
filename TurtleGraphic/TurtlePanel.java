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

        controlJPanel1.setBackground(Color.PINK);
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

        JButton ClearButton = new JButton("Clear");
        ClearButton.addActionListener(this);
        controlJPanel1.add(ClearButton);

        JButton ColorButton = new JButton("Color");
        ColorButton.addActionListener(this);
        controlJPanel1.add(ColorButton);

        frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Turtle Graphic");
        frame.setSize(FRAME_WIDTH+10, FRAME_HEIGHT+10);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar(){
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        // this keyword means TurtlePanel
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New", "Save", "Open", "Quit"}, this);
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
        } else if(cmnd == "North"){
            int steps = Integer.parseInt(Utilities.ask("How many steps"));
            model.turn(Heading.NORTH);
            model.move(steps);
        } else if(cmnd == "South"){
            int steps = Integer.parseInt(Utilities.ask("How many steps"));
            model.turn(Heading.SOUTH);
            model.move(steps);
        }else if(cmnd == "East"){
            int steps = Integer.parseInt(Utilities.ask("How many steps"));
            model.turn(Heading.EAST);
            model.move(steps);
        }else if(cmnd == "West"){
            int steps = Integer.parseInt(Utilities.ask("How many steps"));
            model.turn(Heading.WEST);
            model.move(steps);
            System.out.println("East");
        }else if(cmnd == "Clear"){
            model.clearPath();
        }else if(cmnd == "Color"){
            Color newColor = JColorChooser.showDialog(null, "Choose a color", model.getCur().getColor());
            model.getCur().setColor(newColor);
        }else if (cmnd == "Save") {
            try {
                //String fName = Utilities.ask("File Name?");
                String fName = Utilities.getFileName(null, false);
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                os.writeObject(model);
                os.close();
            } catch (Exception err) {
                Utilities.error("Invalid file format!!!!");
            }
        }else if (cmnd == "Open") {
            try {
                String fName = Utilities.getFileName(null, true);
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                //model.removePropertyChangeListener(this);
                model = (Turtle) is.readObject();
                //this.model.initSupport();
                //model.addPropertyChangeListener(this);
                view.setModel(model);
                is.close();
            } catch (Exception err) {
                Utilities.error(err.getMessage());
            }
        } else if (cmnd == "New") {
            model = new Turtle();
            view.setModel(model);
        } else if (cmnd == "Quit") {
            //Utilities.saveChanges(model);
            System.exit(1);
        } else  {
            Utilities.error("Unrecognized command: " + cmnd);
        }
    }


    public static void main(String[] args) {
        // write your code here
        System.out.println("Test");
        TurtlePanel app = new TurtlePanel();
    }
}