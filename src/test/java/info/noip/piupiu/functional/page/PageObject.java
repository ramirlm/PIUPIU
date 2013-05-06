/**
 *
 */
package info.noip.piupiu.functional.page;

import com.thoughtworks.selenium.Selenium;

/**
 * @author efreitas
 *
 */
public class PageObject {

	private Selenium selenium;

	public PageObject(Selenium selenium){
		this.selenium = selenium;
	}
	public String retornarTitulo(){
		return selenium.getTitle();
	}

	public void acessarPagina(){
		acessarUrl("");
	}

	public boolean verificarTextoPresente(String textoASerVerificado){
		return selenium.isTextPresent(textoASerVerificado);
	}

	public void acessarLink(String link){
		selenium.click(link);
		selenium.waitForPageToLoad("30000");
	}

	/**
	 * Esse metodo, passando o timeout foi criado para casos em que o retorno do click é uma resposta em ajax,
	 * ou seja, a página não é recarregada. Logo, o selenium não precisa esperar...
	 * @param link
	 * @param timeout
	 */
	public void acessarLink(String link, String timeout){
		selenium.click(link);
		selenium.waitForPageToLoad(timeout);
	}

	public void acessarUrl(String url){
		selenium.open(url);
		selenium.waitForPageToLoad("30000");
	}

	public void preencherCampo(String campo, String valor){
		selenium.type(campo, valor);
	}

	public void preencherCampoAutoComplete(String campo, String valor){
		selenium.typeKeys(campo, valor);
		selenium.setSpeed("2000");
	}


	/**
	 * Esse valor de 2000ms, foi setado para esperar que o ajax populasse as selectedbox dependentes
	 * @param campo
	 * @param valor
	 */
	public void setarValorSelectBox(String campo, String valor){
		selenium.select(campo, valor);
		selenium.setSpeed("2000");
	}

	public void acessarLinkAjax(String link){
		selenium.click(link);
		selenium.setSpeed("1000");
	}
}
