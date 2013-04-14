package info.noip.piupiu.controller;

import info.noip.piupiu.model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class LoginController {

	private final Result result;

	public LoginController(Result result) {
		this.result = result;
	}

	@Path("/login")
	@Post
	public void login(User user) {
		Boolean sucess = doLogin(user);
		if (sucess) {
			result.redirectTo(ProfilesController.class).show(user);	
		}else{
			result.redirectTo(IndexController.class).index();
		}
	}

	private Boolean doLogin(User user) {
		return true;
	}

}
