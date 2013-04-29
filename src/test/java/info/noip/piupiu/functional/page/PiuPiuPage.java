package info.noip.piupiu.functional.page;

import com.thoughtworks.selenium.Selenium;

public class PiuPiuPage extends PageObject {

	public PiuPiuPage(Selenium selenium) {
		super(selenium);
	}
	
	public void login(String username, String senha){
		preencherCampo("emailLogin", username);
		preencherCampo("passwordLogin", senha);
		acessarLink("css=form[name=\"registerForm\"] > button.btn");
	}
	
	


}
