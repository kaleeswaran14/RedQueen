package com.rq.selenium.corp;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.DefaultSelenium;
import junit.framework.TestCase;
import org.openqa.selenium.server.SeleniumServer;

public class WhenSearchingForDrupalUsingGoogleTest
   extends TestCase
{
   private static final String MAX_WAIT_TIME_IN_MS = "60000";
   private static final String BASE_URL = "http://www.bitmotif.com";
   private Selenium selenium = new DefaultSelenium( "localhost", 4444, "*firefox", BASE_URL);
   private SeleniumServer seleniumServer;
   public void setUp()
      throws Exception
   {
      seleniumServer = new SeleniumServer();
      seleniumServer.start();
      selenium.start();
   }

   public void tearDown()
      throws Exception
   {
      selenium.stop();
      seleniumServer.stop();
   }

   public void testClickingLink()
      throws Exception
   {
      selenium.open(BASE_URL);
      selenium.click("link=Test Page For Selenium Remote Control");
      selenium.waitForPageToLoad(MAX_WAIT_TIME_IN_MS);

      String expectedTitle = "Bit Motif » Test Page For Selenium Remote Control";
      assertEquals(expectedTitle, selenium.getTitle());
   }
}