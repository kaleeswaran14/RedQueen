package com.photon.rest;

import java.io.*;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.commons.lang.*;
import org.codehaus.plexus.util.cli.*;

@Path("/execute")
public class DeployService {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String execute(@QueryParam("command") String command, @QueryParam("directory") String directory, @Context UriInfo ui, @Context HttpHeaders hh) {
		MultivaluedMap<String, String> headerParams = hh.getRequestHeaders();
	    Map<String, Cookie> cookies = hh.getCookies();
	    System.out.println("headerParams "+ headerParams);
	    
	    System.out.println("Command is " + command);
	    System.out.println("Directory is " + directory);
	    
	    // Throw error when command or dir is empty
	    if (StringUtils.isEmpty(command) || StringUtils.isEmpty(directory)) {
	    	return "<html> " + "<title>" + "Error " + "</title>" + "<body><h4>" + "Command or Directory is empty, pass the query string as"+ "</h4><h1>" +"http://localhost:8080/DeployService/rest/execute?command=mvn clean test&direcotry=user" + "</body></h1>" + "</html> ";
	    }
	    
	    // Execution directory
		File dir = new File(directory);
	    
	    // Throw error when dir does not exist
	    if (!dir.exists()) {
	    	return "<html> " + "<title>" + "Invalid Directory " + "</title>" + "<body><h4>" + "Invalid Directory"+ "</h4><h1>" + "Directory that you specified does not exist " + "</body></h1>" + "</html> ";
	    }
	    
	    // Process execution
	    execute(command, directory);
	    
	    // Successfull execution
	    return "<html> " + "<title>" + "Execution sucess" + "</title>" + "<body><h1>" + "Execution sucess" + "</body></h1>" + "</html> ";
	}

	private void execute(String command, String directory) {
		// Process execution
	    try {
	    	Commandline cl = new Commandline(command.toString());
	    	cl.setWorkingDirectory(directory);
            Process process = cl.execute();
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.readLine() != null) {
				System.out.println(bufferedReader.readLine());
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}