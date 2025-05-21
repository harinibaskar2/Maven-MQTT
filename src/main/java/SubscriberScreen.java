
import javax.swing.*;
import  java.awt.*;

/**
 * A simple GUI panel that draws a circle based on coordinates
 * stored in the Repository (updated by the MQTT Subscriber).
 * 
 * Author: Darien Rodrigo
 */

public class SubscriberScreen extends JPanel {
    public SubscriberScreen(){
        setBackground(new Color(255,255,255));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Repository repo = Repository.getInstance();
        int x = repo.getBallX();
        int y = repo.getBallY();

        g.setColor(Color.BLACK);
        g.fillOval(x-10, y-10, 20, 20);
    }

    public static void main(String[] args) {
        Subscriber subscriber = new Subscriber();
        Thread mqttThread = new Thread(subscriber);
        mqttThread.start();

        JFrame frame = new JFrame("MQTT Circle Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setResizable(false);

        SubscriberScreen screen = new SubscriberScreen();
        frame.add(screen);
        frame.setVisible(true);
    }
}
