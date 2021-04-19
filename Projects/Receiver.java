package Projects;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Receiver {

	private static final int SENDER_PORT_NUMBER = 64_000;
	private static final int RECEIVER_PORT_NUMBER =64_500;
	
	private static final boolean BROADCAST_ENABLED = false;
	
	public static void main(String[]args) {
		Socket receiveSocket = new Socket(RECEIVER_PORT_NUMBER, BROADCAST_ENABLED);
		
		while(true) {
			DatagramPacket receivePacket = receiveSocket.receive();
			
			if(receivePacket !=null) {
				byte[] receiveBytes  = receivePacket.getData();
				String message = new String(receiveBytes);
				
				InetAddress sourceAddress = receivePacket.getAddress();
				int sourcePort = receivePacket.getPort();
				
				System.out.println();
			}
		}
		
	}
}
