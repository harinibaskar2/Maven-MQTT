
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.w3c.dom.events.MouseEvent;

/**
 * A simple GUI panel that draws a circle based on coordinates
 * stored in the Repository (updated by the MQTT Subscriber).
 * 
 * Author: Darien Rodrigo
 */

public class SubscriberScreen extends JPanel implements ActionListener, MouseMotionListener, MouseListener {
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

    @Override
    public void mouseMoved(MouseEvent e) {
        if (Repository.getInstance().getWhoAmI() == Repository.CLIENT)
            Repository.getInstance().setClientPlayerY(e.getY());
        else
            Repository.getInstance().setServerPlayerY(e.getY());
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Repository.getInstance().moveBall();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        int ballX = Repository.getInstance().getBallX();
        int ballY = Repository.getInstance().getBallY();

        if (mouseX >= ballX - 5 && mouseX <= ballX + 5 &&
                mouseY >= ballY - 5 && mouseY <= ballY + 5) {
            System.out.println("Ball clicked at: " + mouseX + ", " + mouseY);
            Repository.getInstance().setCoordinates(mouseX, mouseY);
        }
    }


    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
    }

    
}
