

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;






/**
 * This class is a simple JPanel that will be used to display the Bars, Ball, and Chat.
 *
 * @author hbaskar
 * @version 1.1
 */





public class PongPanel extends JPanel implements MouseMotionListener, MouseListener {


        public PongPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        // Start a timer for ball movement every 50ms
        Timer timer = new Timer(50, this);
        timer.start();
    }



  
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCenterLine(g);



    }



    private void drawCenterLine(Graphics g) {
        g.setColor(Color.WHITE);
        Graphics2D g2d = (Graphics2D) g;
        float[] dash1 = {10.0f};
        BasicStroke dashed = new BasicStroke(
                1.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER,
                10.0f,
                dash1,
                0.0f);
        g2d.setStroke(dashed);
        g.drawLine(400, 0, 400, 600);
        g2d.setStroke(new BasicStroke());
    }




    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}






@Override
public void mouseClicked(MouseEvent e) {
    int mouseX = e.getX();
    int mouseY = e.getY();

    System.out.println("Screen clicked at: " + mouseX + ", " + mouseY);

    // Store coordinates
    Repository.getInstance().setCoordinates(mouseX, mouseY);

    // Publish coordinates to MQTT
    MqttPublisher.publishCoordinates(mouseX, mouseY);
}

} 



