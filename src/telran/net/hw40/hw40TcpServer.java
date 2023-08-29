package telran.net.hw40;
import java.net.*;
import java.io.*;

public class hw40TcpServer {

	public static final int PORT = 5000;
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server is listening to por" + PORT);
		while(true) {
			Socket socket = serverSocket.accept();
			clientRun(socket);
		}
		
		
		
	}
	private static void clientRun(Socket socket) {
		try(BufferedReader reader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
				PrintStream writer = new PrintStream(socket.getOutputStream())) {
				
			while(true) {
				String line = reader.readLine();
				if(line == null) {
					System.out.println("client closed normaly connection");
					break;
				}
				String response = getResponse(line);
				writer.println(response);
			}
			
		} catch (Exception e) {
			System.out.println("client closed abnormally connection");
		}
	}
	private static String getResponse(String line) {
		// <request type>#<string>
		// request type := "length" | "reverse"
		
		String response = "Wrong request structure, usage:<request type>#<string> ";
		String [] tokens = line.split("#");
		if(tokens.length == 3) {
			try {
				double[] operands = getOperands(tokens);
				response = switch(tokens[0]) {
				case "add" -> Double.toString(operands[0] + operands[1]);
				case "minus" -> Double.toString(operands[0] - operands[1]);
				case "multiply" -> Double.toString(operands[0] * operands[1]);
				case "divide" -> Double.toString(operands[0] / operands[1]);
				default -> "wrong request type";
				};
			} catch (Exception e) {
				response = e.getMessage();
			}
		}
		return response;
	}
	private static double[] getOperands(String[] tokens) throws Exception {
		try {
			double op1 = Double.parseDouble(tokens[1]);
			double op2 = Double.parseDouble(tokens[2]);
			return new double[] {op1, op2};
		} catch (NumberFormatException e) {
			throw new Exception ("all operands must be any numbers " + e.getMessage());
		}
	}
}
