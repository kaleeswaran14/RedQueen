package src.test.java;

import java.sql.*;
import java.util.*;

import org.junit.*;


public class AppTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Setup .... ");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown .... ");
	}
	
//	@Test
	public void testTheApp() throws Exception {
		Connection conn = null;  
		try {
//			WARNING: Could not obtain connection metadata
//			java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:
			System.out.println("AppTest started.... ");
			Class.forName("com.mysql.jdbc.Driver");
			Properties connectionProperties = new Properties();
			connectionProperties.put("user", "java");
			connectionProperties.put("password", "java");
			conn = DriverManager.getConnection("jdbc:mysql://172.16.23.59:8889/java", connectionProperties);
			System.out.println("Connection success ...... ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally  
        {  
            if (conn != null)  
            {  
                try  
                {  
                    conn.close ();  
                    System.out.println ("Database connection terminated");  
                }  
                catch (Exception e) { /* ignore close errors */ }  
            }  
        }
	}

}
