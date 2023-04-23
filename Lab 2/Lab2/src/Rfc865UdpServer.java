import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Rfc865UdpServer {
	public static void main(String args[]) {
		// 1. Open UDP socket at well-known port
		DatagramSocket socket = null;
		int portNumber = 17;
		
		try {
			InetAddress inet = InetAddress.getLocalHost();
			socket = new DatagramSocket(portNumber);
		} catch (SocketException e) { System.out.println("Cannot connect to socket"); }  
		catch (UnknownHostException e) {}
		
		while (true) {
			try {
				// 2. Listen for UDP request from client				
				byte[] receive_buf = new byte[512];
				DatagramPacket request = new DatagramPacket(receive_buf, receive_buf.length);	
				socket.receive(request);
				
				System.out.println(new String(receive_buf));
				
				// 3. Send UDP reply to client
				byte[] reply_buf = new byte[512];
				reply_buf = "ok received liao".getBytes();
				DatagramPacket reply = new DatagramPacket(reply_buf, reply_buf.length, request.getSocketAddress());
				socket.send(reply);
			} catch (IOException e){
				;
			}
		}
	}
}


