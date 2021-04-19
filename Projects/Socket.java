package Projects;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;



public class Socket {
	
	private static final int MAXIMUM_SIZE_OF_INCOMING_MESSAGE = 1024;
	
	public static final int UNABLE_TO_OBTAIN_LOCAL_HOST_ADDRESS = -1;
	public static final int UNABLE_TO_OPEN_DATAGRAM_SOCKET = -2;
	public static final int UNABLE_TO_SEND_DATAGRAM_PACKET = -3;
	public static final int UNABLE_TO_RECEIVE_DATAGRAM_PACKET = -4;
	public static final int UNABLE_TO_SET_SOCKET_TIMEOUT = -5;
	
	private int myPortNumber;
	private DatagramSocket mySocket;
	private InetAddress myIPAddress;
	private Thread receiveThread;
	static public boolean ReceiveThreadShouldKeepRunning =true;
	
	private ConcurrentLinkedQueue<DatagramPacket> packetQueue = new ConcurrentLinkedQueue<DatagramPacket>();
	
	public Socket(int myPortNumber, boolean allowReceivingBroadcast) {
		this.myPortNumber = myPortNumber;
		System.out.println("My port number:" + this.myPortNumber);
		
		try {
			this.myIPAddress = InetAddress.getLocalHost();
		}catch(UnknownHostException e) {
			e.printStackTrace();
			System.exit(UNABLE_TO_OBTAIN_LOCAL_HOST_ADDRESS);
		}
		
		System.out.println("My IP address" + this.myIPAddress.getHostAddress());
		
		try {
			if(allowReceivingBroadcast) {
				this.mySocket = new DatagramSocket(this.myPortNumber);
			}else {
				this.mySocket  = new DatagramSocket(this.myPortNumber,this.myIPAddress);
			}
		}catch(SocketException e){
			e.printStackTrace();
			System.out.println(UNABLE_TO_OPEN_DATAGRAM_SOCKET);
			
		}
		
		this.receiveThread = new Thread(
				new Runnable() {
					@Override
					public void run() {
						receiveThread();
					}
					
				});
		this.receiveThread.setName("Receive Thread for Port = " + this.myPortNumber);
		this.receiveThread.start();
	}
	
	public void send(String message, 
			InetAddress destinationIPAddress, 
			int destinationPortNumber ) {
		
		byte[] buffer  = message.getBytes();
		
		DatagramPacket outPacket = new DatagramPacket(buffer, buffer.length, 
													destinationIPAddress, destinationPortNumber);
	
		try{
			mySocket.send(outPacket);
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(UNABLE_TO_SEND_DATAGRAM_PACKET);
		}
	
	}
	
	public void receiveThread() {
		
		// to do: complete thread for receiving.
		
		
		System.out.println("Receive thread is starting");
		try {
			this.mySocket.setSoTimeout(50);
		}catch(SocketException e1) {
			e1.printStackTrace();
			System.exit(UNABLE_TO_SET_SOCKET_TIMEOUT);
		}
		
		do {
		
		byte[] inbuffer = new byte[MAXIMUM_SIZE_OF_INCOMING_MESSAGE];
		
		for(int i = 0;i<inbuffer.length;i++) {
			inbuffer[i] =' ';
		}
		
		DatagramPacket inPacket = new DatagramPacket(inbuffer, inbuffer.length);
		
		try{
			this.mySocket.receive(inPacket);
			packetQueue.add(inPacket);
		}catch(SocketTimeoutException ste) {
			
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(UNABLE_TO_RECEIVE_DATAGRAM_PACKET);
		}
		}while(ReceiveThreadShouldKeepRunning);
		
		System.out.println("Receive Thread is Stopping!!");

	}
	
	public void close() {
		Socket.ReceiveThreadShouldKeepRunning = false;
		
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.mySocket.close();
		System.out.println("Datagram socket has been closed.");
	}
	
	public InetAddress getIPaddress() {
		return myIPAddress;
	}
	
	public int getMyPortNumber() {
		return myPortNumber;
	}
	
}
