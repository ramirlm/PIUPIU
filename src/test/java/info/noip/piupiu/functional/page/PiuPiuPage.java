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

	public void piar(String texto){
		preencherCampo("id=new_message", texto);
		acessarLinkAjax("css=button.btn.btn-info");
	}

	public void pesquisarUsuario(String usuario){
		String usuarioType = usuario.substring(0, usuario.length()-1);
		preencherCampo("id=search", usuarioType);
		preencherCampoAutoComplete("id=search", usuario.substring(usuario.length()-1));
		acessarLinkAjax("css=strong");
	}


}
