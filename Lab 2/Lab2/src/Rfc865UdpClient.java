import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Rfc865UdpClient {
	public static void main(String args[]) {
		// 1. Open UDP socket at well-known port
		DatagramSocket socket = null;
		int portNumber = 17;
		
		try {
//			InetAddress inet = InetAddress.getLocalHost();
			InetAddress inet = InetAddress.getByName("hwlab1.scse.ntu.edu.sg");
			socket = new DatagramSocket();
			socket.connect(inet, portNumber);
		} catch (SocketException e) { System.out.println("Cannot connect to socket"); }  
		catch (UnknownHostException e) {}
		
		try {
			// 2. Send UDP request to server
			byte[] send_buf = new byte[512];
			send_buf = String.format("Bryan Lu We Zhern, A52, %s", InetAddress.getLocalHost().getHostAddress().toString()).getBytes();
			DatagramPacket request = new DatagramPacket(send_buf, send_buf.length);
			socket.send(request);
			
			// 3. Listen for UDP reply from server
			byte[] receive_buf = new byte[512];
			DatagramPacket reply = new DatagramPacket(receive_buf, receive_buf.length);	
			socket.receive(reply);
			
		} catch (IOException e){
			;
		}
		
	}
}
