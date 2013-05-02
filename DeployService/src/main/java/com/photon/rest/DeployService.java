package com.photon.rest;

import java.io.*;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.commons.lang.*;
import org.codehaus.plexus.util.cli.*;

@Path("/execute")
public class DeployService {

//	@GET
//	@Path("/{param}")
//	public Response getMsg(@PathParam("param") String msg) {
//		String output = "Message is : " + msg;
//		return Response.status(200).entity(output).build();
//	}

	// This method is called if TEXT_PLAIN is request
//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String sayPlainTextHello() {
//		return "Hello Jersey";
//	}

//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String sayPlainTextHello(@Context UriInfo ui) {
//		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
//		System.out.println("queryParams > " + queryParams);
//	    MultivaluedMap<String, String> pathParams = ui.getPathParameters();
//	    System.out.println("pathParams > " + pathParams);
//		return "Hello Jersey";
//	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayPlainTextHello(@QueryParam("command") String command, @QueryParam("directory") String directory, @Context UriInfo ui) {
//		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
//		System.out.println("queryParams > " + queryParams);
//	    MultivaluedMap<String, String> pathParams = ui.getPathParameters();
//	    System.out.println("pathParams > " + pathParams);
	    System.out.println("command > " + command);
	    System.out.println("directory > " + directory);
	    
	    // Throw error when command or dir is empty
	    if (StringUtils.isEmpty(command) || StringUtils.isEmpty(directory)) {
//	    	return "command is empty pass the query string as http://localhost:8080/DeployService/rest/execute?command=mvn clean test&direcotry=user";
	    	return "<html> " + "<title>" + "Command or Directory is empty, pass the query string as " + "</title>" + "<body><h1>" + "http://localhost:8080/DeployService/rest/execute?command=mvn clean test&direcotry=user" + "</body></h1>" + "</html> ";
	    }
	    
	    // Execution directory
		File dir = new File(directory);
	    
	    // Throw error when dir does not exist
	    if (!dir.exists()) {
	    	return "<html> " + "<title>" + "Invalid Directory " + "</title>" + "<body><h1>" + "Directory that you specified is not exist " + "</body></h1>" + "</html> ";
	    }
	    
	    // Process execution
	    try {
	    	Commandline cl = new Commandline(command.toString());
	    	cl.setWorkingDirectory(directory);
            Process process = cl.execute();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while (bufferedReader.readLine() != null) {
				System.out.println(bufferedReader.readLine());
			}
        } catch (Exception e) {
            e.printStackTrace();
        }	    	
	    return "Execution sucess ";
	}
	
	
//	// This method is called if XML is request
//	@GET
//	@Produces(MediaType.TEXT_XML)
//	public String sayXMLHello() {
//		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey xml" + "</hello>";
//	}
//
//	// This method is called if HTML is request
//	@GET
//	@Produces(MediaType.TEXT_HTML)
//	public String sayHtmlHello() {
//		return "<html> " + "<title>" + "Hello Jersey" + "</title>"
//				+ "<body><h1>" + "Hello Jersey html" + "</body></h1>" + "</html> ";
//	}

}