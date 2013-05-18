package info.noip.piupiu.functional.page.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import info.noip.piupiu.functional.page.PiuPiuPage;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.CommandProcessor;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.HttpCommandProcessor;
import com.thoughtworks.selenium.Selenium;

@Ignore
public class PiuPiuTest {

	private static SeleniumServer server;
	private static Selenium selenium;
	private static CommandProcessor proc;
	private static PiuPiuPage page;

	@BeforeClass
	public static void setupBeforeSuite() {
	  String seleniumHost = "localhost";
	  String seleniumPort = "3737";
	  String seleniumBrowser = "firefox";
	  String seleniumUrl = "http://localhost:8080/piupiu/";

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
	  page = new PiuPiuPage(selenium);
	}

	@AfterClass
	public static void setupAfterSuite() {
	  selenium.stop();
	  server.stop();
	}

	public void shouldOpenMainPage(){
		page.acessarPagina();
		assertEquals("Bem-vindo ao Piu-Piu",page.retornarTitulo());
	}


	public void shouldNotLogin(){
		page.acessarPagina();
		page.login("andersonsilva@ufc.com", "thespider");
		assertTrue(page.verificarTextoPresente("- Usuário e/ou senha incorretos."));

	}

	public void shouldLogin(){
		page.acessarPagina();
		page.login("testeselenium@gmail.com", "selenium123");
		assertTrue(page.verificarTextoPresente("Logado como testeselenium@gmail.com"));
	}

	public void shouldPiar(){
		long currentTimes = System.currentTimeMillis();
		page.acessarPagina();
		page.login("testeselenium@gmail.com", "selenium123");
		page.piar("Piando agora "+currentTimes);
		assertTrue(page.verificarTextoPresente("Piando agora "+currentTimes));
	}

	public void shouldPesquisarUsuario(){
		page.acessarPagina();
		page.login("testeselenium@gmail.com", "selenium123");
		page.pesquisarUsuario("eduardo");
		assertTrue(page.verificarTextoPresente("Eduardo Henrique"));
	}

}
