import org.eclipse.paho.client.mqttv3.*;

/**
 * This class is a simple MQTT subscriber that listens to a TOPIC.
 * The BROKER is broker.hivemq.com and the TOPIC is cal-poly/csc/307.
 * (run this and the publisher at the same time)
 * 
 * We're looking for the X and Y coordinates that come from the publisher to be able to draw on the screen
 * When a message is taken in, we call the screen to repaint itself
 *
 * @author Darien Rodrigo
 * @version 1.0
 * 
 * 
 * being implemented by Darien Rodrigo
 */
public class Subscriber implements MqttCallback, Runnable {
	
	private final static String BROKER = "broker.hivemq.com";
	private final static String TOPIC = "cal-poly/csc/307";
	private final static String CLIENT_ID = "user2";

	private SubscriberScreen subscriberScreen;
	
	public static void main(String[] args) {
		Subscriber subscriber = new Subscriber();
	}
	@Override
	public void run(){
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

		String incoming = new String(mqttMessage.getPayload());
		String content[] = incoming.split(",");
		int x = Integer.parseInt(content[0]);
		int y = Integer.parseInt(content[1]);
		Repository repo = repo.getInstance();
		repo.setCoordinates(x, y);

		if (subscriberScreen != null){
			subscriberScreen.repaint();
		}


	}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
	}

}