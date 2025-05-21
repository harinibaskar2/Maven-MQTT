import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is a simple JPanel that will be used to display the Bars, Ball, and Chat.
 *
 * @author hbaskar
 * @version 1.1
 */
public class PongPanel extends JPanel implements MouseMotionListener, MouseListener, ActionListener {

    public PongPanel() {
        addMouseListener(this);
        addMouseMotionListener(this);
        // Start a timer for any future updates (if needed)
        Timer timer = new Timer(50, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    // Empty implementations for MouseMotionListener
    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    // Empty implementations for MouseListener except mouseClicked
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        System.out.println("Screen clicked at: " + mouseX + ", " + mouseY);

        // Store coordinates in Repository singleton
        Repository.getInstance().setCoordinates(mouseX, mouseY);

        // Publish coordinates via MQTT publisher class
        MqttPublisher.publishCoordinates(mouseX, mouseY);
    }

    // Timer callback (required by ActionListener)
    @Override
    public void actionPerformed(ActionEvent e) {
        // If needed, update the panel or repaint here
        // For now, just repaint
        repaint();
    }
}
