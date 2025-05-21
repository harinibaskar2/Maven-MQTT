import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Repository {
    private static Repository instance;

    private int x;
    private int y;

    private MqttClient client;
    private final String BROKER = "tcp://test.mosquitto.org:1883";
    private final String TOPIC = "cal-poly/csc/309";

    private Repository() {
        try {
            client = new MqttClient(BROKER, MqttClient.generateClientId());
            client.connect();
            System.out.println("Connected to MQTT Broker");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static Repository getInstance() {
        if (instance == null) {
            synchronized (Repository.class) {
                if (instance == null) {
                    instance = new Repository();
                }
            }
        }
        return instance;
    }

    public synchronized void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
        publishCoordinates();  // publish immediately after updating
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized int getY() {
        return y;
    }

    private synchronized void publishCoordinates() {
        if (client == null || !client.isConnected()) {
            System.err.println("MQTT client not connected");
            return;
        }

        String payload = String.format("%d,%d", x, y);
        try {
            MqttMessage message = new MqttMessage(payload.getBytes());
            message.setQos(2);
            client.publish(TOPIC, message);
            System.out.println("Published coordinates: " + payload);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (client != null && client.isConnected()) {
                client.disconnect();
                System.out.println("Disconnected from MQTT Broker");
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
