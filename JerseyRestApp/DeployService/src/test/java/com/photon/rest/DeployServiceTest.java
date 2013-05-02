package com.photon.rest;

import java.io.*;

import org.apache.commons.lang.*;
import org.codehaus.plexus.util.cli.*;
import org.junit.*;

public class DeployServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws InterruptedException {
		//http://localhost:8080/DeployService/rest/execute?command=mvn%20clean&directory=/Users/kaleeswaran/Downloads/RESTfulExample
//		curl -v -L -G -d "command=mvn%20clean&directory=/Users/kaleeswaran/Downloads/RESTfulExample" http://172.16.23.59:8080/DeployService/rest/execute
//		http://stackoverflow.com/questions/9100099/why-is-curl-truncating-this-query-string
//		curl -v -L "http://172.16.23.59:8080/DeployService/rest/execute?command=mvn%20clean&directory=/Users/kaleeswaran/Downloads/RESTfulExample"
		
//		http://www.ibm.com/developerworks/web/library/wa-aj-tomcat/
		String command = "mvn clean";
		String directory = "/Users/kaleeswaran/Downloads/RESTfulExample";
		
	    System.out.println("Command is " + command);
	    System.out.println("Directory is " + directory);
	    
	    // Throw error when command or dir is empty
	    if (StringUtils.isEmpty(command) || StringUtils.isEmpty(directory)) {
	    	System.out.println("Command or Directory is empty, pass the query string as http://localhost:8080/DeployService/rest/execute?command=mvn clean test&direcotry=user");
	    }
	    
	    // Execution directory
		File dir = new File(directory);
	    
	    // Throw error when dir does not exist
	    if (!dir.exists()) {
	    	System.out.println("Invalid Directory Directory that you specified is not exist ");
	    }
	    
	    // Process execution
	    for (int i = 0; i < 5; i++) {
	    	execute(command, directory);			
		}
	    
	    Thread.sleep(40000);
	    System.out.println("Execution sucess ");
	    
	}

	private void execute(final String command, final String directory) {
		try {
			Runnable executeRunnable = new Runnable() {
				public void run() {
					try {
						System.out.println("################ STARTED ################");
						Commandline cl = new Commandline(command.toString());
						cl.setWorkingDirectory(directory);
						Process process = cl.execute();
						InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
						BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
						while (bufferedReader.readLine() != null) {
							System.out.println(bufferedReader.readLine());
						}
						System.out.println("################ COMPLETED ################");
					} catch (Exception e) {
						e.printStackTrace();
					}		
				}
			};
			Thread t = new Thread(executeRunnable, "executeRunnable");
			t.start();
			
//	    	Commandline cl = new Commandline(command.toString());
//	    	cl.setWorkingDirectory(directory);
//            Process process = cl.execute();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            while (bufferedReader.readLine() != null) {
//				System.out.println(bufferedReader.readLine());
//			}
			
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
//	@Test
	public void userHome() {
		String homeDir = System.getProperty("user.home");
		System.out.println("homeDir > " + homeDir);
	}

}
