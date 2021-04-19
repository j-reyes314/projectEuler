package Projects;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Sender {

	private static final int SENDER_PORT_NUMBER = 64_000;
	private static final int RECEIVER_PORT_NUMBER =64_500;
	private static final int NUMBER_OF_MESSAGES_TO_SEND =10;
	
	private static final boolean BROADCAST_ENABLED = false;
	private static final String DESTINATION_IP_ADDRESS = "0";
	
	private static final int UNABLE_TO_OBTAIN_IP_ADDRESS =-10;
	
	public static void main(String []args) {
		Socket sendSocket = new Socket(SENDER_PORT_NUMBER, BROADCAST_ENABLED);
		
		
		InetAddress destinationIPAddress = null;
		try {
		destinationIPAddress = InetAddress.getByName(DESTINATION_IP_ADDRESS);
		}catch(UnknownHostException e) {
			e.printStackTrace();
			System.exit(UNABLE_TO_OBTAIN_IP_ADDRESS);
		}
		
		for(int i =1; i<NUMBER_OF_MESSAGES_TO_SEND+1; i++) {
			String message = new String("Hello there! This is message"+ i);
			
			System.out.println("Sending Message:" + message);
			
			sendSocket.send(message, destinationIPAddress, RECEIVER_PORT_NUMBER);
		}
		
		sendSocket.close();
	}
}
