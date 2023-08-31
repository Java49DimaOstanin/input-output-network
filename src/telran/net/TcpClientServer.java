package telran.net;
import java.net.*;
import java.io.*;
public class TcpClientServer implements Runnable {
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	ApplProtocol protocol;
	public TcpClientServer(Socket socket,ApplProtocol protocol) throws IOException {
		this.socket = socket;
		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());
		this.protocol = protocol;
	}
	
	
	@Override
	public void run() {
		try {
			while(true) {
				Request request = (Request) input.readObject();
				Response response = protocol.getResponse(request);
				output.writeObject(response);
			}
		}catch(EOFException e) {
			System.out.println("client closed normalyy connection");
		}catch (Exception e) {
			System.out.println("client closed abnormalyy connection" + e.getMessage());
		}
	}

}