import org.eclipse.paho.client.mqttv3.*;

/**
 * This class is a simple MQTT subscriber that listens to a TOPIC.
 * The BROKER is test.mosquitto.org and the TOPIC is cal-poly/csc/309.
 * (run this and the publisher at the same time)
 *
 * @author Darien Rodrigo
 * @version 1.0
 * 
 * 
 * being implemented by Darien Rodrigo
 */
public class Subscriber implements MqttCallback {
	
	private final static String BROKER = "broker.hivemq.com";
	private final static String TOPIC = "cal-poly/csc/307";
	private final static String CLIENT_ID = "user2";
	
	public static void main(String[] args) {
		try {
			MqttClient client = new MqttClient(BROKER, CLIENT_ID);
			Subscriber subscriber = new Subscriber();
			client.setCallback(subscriber);
			client.connect();
			System.out.println("Connected to BROKER: " + BROKER);
			client.subscribe(TOPIC);
			System.out.println("Subscribed to TOPIC: " + TOPIC);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection lost: " + throwable.getMessage());
	}
	
	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) {
		System.out.println("Message arrived. Topic: " + s +
			" Message: " + new String(mqttMessage.getPayload()));
	}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
	}

}