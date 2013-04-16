package com.mkyong.listener;

import java.net.URL;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateListener implements ServletContextListener{
 
	private Configuration config;
    private SessionFactory factory;
    private String path = "/hibernate.cfg.xml";
    private static Class clazz = HibernateListener.class;
 
   public static final String KEY_NAME = clazz.getName();

	   
	public void contextDestroyed(ServletContextEvent event) {
		//
	}
 
	public void contextInitialized(ServletContextEvent event) {
 
		try {
			 System.out.println("Listener class instantiation .............................. ");
	         URL url = HibernateListener.class.getResource(path);
	         config = new Configuration().configure(url);
	         factory = config.buildSessionFactory();
	         System.out.println("factory obj > " + factory);
	         //save the Hibernate session factory into serlvet context
	         event.getServletContext().setAttribute(KEY_NAME, factory);
                 System.out.println("Initiated ..###################.... ");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	}
}