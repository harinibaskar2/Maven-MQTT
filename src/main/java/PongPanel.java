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
        // Start a timer for updates
        Timer timer = new Timer(50, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Clear the panel
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Get coordinates from Repository
        Repository repo = Repository.getInstance();
        int x = repo.getX();
        int y = repo.getY();

        // Draw a circle at the coordinates
        g.setColor(Color.BLACK);
        g.fillOval(x - 10, y - 10, 20, 20); // 20x20 circle centered at (x, y)
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    // MouseMotionListener methods
    @Override public void mouseDragged(MouseEvent e) {}
    @Override public void mouseMoved(MouseEvent e) {}

    // MouseListener methods
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

    // Timer callback
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint(); // Repaint on timer tick
    }
}
