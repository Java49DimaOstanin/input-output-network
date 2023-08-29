package telran.net.hw40;
import java.net.*;
import java.util.*;
import java.io.*;
import telran.view.*;
public class hw40Client {
	
	static final String HOST="localhost";
	static final int PORT=5000;
	
	
	public static void main(String[] args) throws Exception {
		try(Socket socket = new Socket(HOST, PORT);
				PrintStream writer = new PrintStream(socket.getOutputStream());
				BufferedReader reader =
						new BufferedReader(new InputStreamReader(socket.getInputStream()))){
			 InputOutput io = new ConsoleInputOutput();
			 Menu menu = new Menu("TCP client example", Item.of("Calculator", io1 -> {
				 HashSet<String> requests = new HashSet<>(Arrays.asList("add", "minus","multiply","divide"));
				 String requestType = io1.readString("Enter request type " + requests, HOST, requests);
				 Double number1 = io1.readDouble("Enter any number", "Wrong number");
				 Double number2 = io1.readDouble("Enter any number", "Wrong number");
				 writer.println(String.format("%s#%f#%f", requestType, number1,number2));
				 try {
					String response = reader.readLine();
					io1.writeLine(response);
				} catch (IOException e) {
					throw new RuntimeException(e.toString());
				}
			 }), Item.ofExit());
			 menu.perform(io);
		}

	}

}
