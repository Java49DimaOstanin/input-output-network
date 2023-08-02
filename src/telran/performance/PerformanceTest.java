package telran.performance;

public abstract class PerformanceTest {

	private String testName;
	private int    nRuns;
	
	public PerformanceTest(String testName, int nRuns) {
		
		this.testName = testName;
		this.nRuns	  = nRuns;
	}
	protected abstract void runTest();
	public void run() {
		long startTime = System.currentTimeMillis();
		for(int i = 0;i < nRuns;i++) {
			runTest();
		}
		long finishTime = System.currentTimeMillis();
		
		System.out.println("Value of Runs:  " + nRuns);
		System.out.println("Test name:      " + testName);
		System.out.println("Running time:   " + (startTime - finishTime) + "ms" );
	}
	
	
	
}
