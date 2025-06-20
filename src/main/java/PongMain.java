import javax.swing.*;





/**
 * Launches the Pong game and MQTT publisher.
 * @author hbaskar
 * @version 1.1
 * 
 */
public class PongMain {
    public static void main(String[] args) {
        Publisher publisher = new Publisher();
        Thread mqttThread = new Thread(publisher);
        mqttThread.start();

        JFrame frame = new JFrame("Pong Game");
        PongPanel pongPanel = new PongPanel();
        frame.add(pongPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            publisher.stop();
            try {
                mqttThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MQTT publisher stopped on exit.");
        }));
    }
}
