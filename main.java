

import javax.swing.*;
import java.awt.*;

public class main extends JFrame{

    public static void main(String[] args) {
        main app = new main();
        app.setSize(800,600);
        app.setTitle("First Attempt at drawing appli");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setVisible(true);
    }

    public main(){
        JPanel drawPanel = new drawpanel();
        JPanel toolPanel = new toolpanel();
        JPanel statusPanel = new statuspanel();
        setLayout(new BorderLayout());
        add(toolPanel,BorderLayout.WEST);
        add(statusPanel,BorderLayout.SOUTH);
        add(drawPanel,BorderLayout.CENTER);

    }
}