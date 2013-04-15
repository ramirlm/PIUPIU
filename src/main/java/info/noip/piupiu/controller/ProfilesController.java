package info.noip.piupiu.controller;

import info.noip.piupiu.model.User;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ProfilesController {

	private Result result;

	public ProfilesController(Result result) {
		this.result = result;
	}

	public void show(User user) {
		result.include("user", user);
	}

}
