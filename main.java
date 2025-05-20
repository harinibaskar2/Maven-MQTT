package Paint;

import javax.swing.*;
import java.awt.*;

public class mainhw extends JFrame{

    public static void main(String[] args) {
        mainhw app = new mainhw();
        app.setSize(800,600);
        app.setTitle("First Attempt at drawing appli");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setVisible(true);
    }

    public mainhw(){
        JPanel drawPanel = new drawpanel();
        JPanel toolPanel = new toolpanel();
        JPanel statusPanel = new statuspanel();
        setLayout(new BorderLayout());
        add(toolPanel,BorderLayout.WEST);
        add(statusPanel,BorderLayout.SOUTH);
        add(drawPanel,BorderLayout.CENTER);

    }
}