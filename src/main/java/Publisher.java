import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Runnable MQTT Publisher that continuously sends coordinates from the Repository
 * to a TOPIC on the test.mosquitto.org broker.
 * 
 * @author hbaskar
 * @version 1.1
 */
public class Publisher implements Runnable {

    private final static String BROKER = "tcp://test.mosquitto.org:1883";
    private final static String TOPIC = "cal-poly/csc/309";
    private final static String CLIENT_ID = "user1";

    private volatile boolean running = true;

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        try {
            MqttClient client = new MqttClient(BROKER, CLIENT_ID);
            client.connect();
            System.out.println("Connected to BROKER: " + BROKER);

            while (running) {
                // Use new generic coordinate getters
                int x = Repository.getInstance().getX();
                int y = Repository.getInstance().getY();

                String content = String.format("%d,%d", x, y);
                MqttMessage message = new MqttMessage(content.getBytes());
                message.setQos(2);

                if (client.isConnected()) {
                    client.publish(TOPIC, message);
                    System.out.println("Published coordinates: " + content);
                }

                Thread.sleep(5000); // wait 5 seconds before sending again
            }

            client.disconnect();
            System.out.println("Disconnected from BROKER.");

        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
