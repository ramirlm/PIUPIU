/**
 * 
 */
package info.noip.piupiu.functional;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;


import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import com.thoughtworks.selenium.Selenium;

/**
 * @author efreitas
 *
 */
public class SeleniumServerTest {
	private SeleniumServer server;
	private Selenium selenium;
	private CommandProcessor proc;
	
	public void setupBeforeSuite() {
	  String seleniumHost = "localhost";
	  String seleniumPort = "3737";
	  String seleniumBrowser = "firefox";
	  String seleniumUrl = "http://localhost:8080/piupiu";
	  
	  RemoteControlConfiguration rcc = new RemoteControlConfiguration();
	  rcc.setSingleWindow(true);
	  rcc.setPort(Integer.parseInt(seleniumPort));
	  
	  try {
	    server = new SeleniumServer(false, rcc);
	    server.boot();
	  } catch (Exception e) {
	    throw new IllegalStateException("Can't start selenium server", e);
	  }
	  
	  proc = new HttpCommandProcessor(seleniumHost, Integer.parseInt(seleniumPort),
	      seleniumBrowser, seleniumUrl);
	  selenium = new DefaultSelenium(proc);
	  selenium.start();
	}
	  
	public void setupAfterSuite() {
	  selenium.stop();
	  server.stop();
	}
	
	public void Test(){
		selenium.open("");
		Assert.assertEquals("Hitek", selenium.getTitle());
	}
}
