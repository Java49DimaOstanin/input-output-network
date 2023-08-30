package telran.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorTcpServerAppl {
	static final int PORT=5000;
	public static void main(String[] args) throws Exception{
		ApplProtocol protocol = new CalculatorProtocol();
		TcpServer server = new TcpServer(PORT, protocol);
		server.run();
	}
}
