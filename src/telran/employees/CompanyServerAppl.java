package telran.employees;

import java.io.IOException;

import telran.employees.service.*;
import telran.net.TcpServer;

public class CompanyServerAppl {
	
	
	private static final int Port = 5000;
	private static final String DEFAULT_FILE_NAME = "employees.data";
	private static  String fileName;
	
	public static void main(String[] args ) throws IOException {
		fileName = args.length > 0? args[0]: DEFAULT_FILE_NAME;
		Company company = new CompanyImpl();
		company.restore(fileName);
		TcpServer tcpServer = new TcpServer(Port, new CompanyProtocol(company));
		tcpServer.run();
	}
}
