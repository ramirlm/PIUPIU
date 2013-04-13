package info.noip.piupiu.controller;

import info.noip.piupiu.model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class LoginController {

	private final Result result;

	private LoginController(Result result) {
		this.result = result;
	}

	@Path("/login")
	@Post
	public void login(User user) {
		result.redirectTo(ProfilesController.class).show(user);
	}

}
