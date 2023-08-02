package telran.performance;
import telran.io.*;


public class CopyPerformanceTest extends PerformanceTest {
	
	
	private String pathToSource;
	private String pathToDestination;
	private CopyFile copyFile;
	

	public CopyPerformanceTest(String testName, int nRuns,String pathToSource,String pathToDestenation,CopyFile copyFile) {
		
		super(testName, nRuns);
		this.pathToSource = pathToSource;
		this.pathToDestination = pathToDestenation;
		this.copyFile = copyFile;
	}

	@Override
	protected void runTest() {
		
		copyFile.copy(pathToSource, pathToDestination);

	}

}
