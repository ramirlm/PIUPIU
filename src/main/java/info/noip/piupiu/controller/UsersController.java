package info.noip.piupiu.controller;

import java.util.ArrayList;
import java.util.List;

import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.infra.MD5Util;
import info.noip.piupiu.model.User;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;

@Resource
public class UsersController {

	private final UsersDao usersDao;
	private final Validator validator;
	private final Result result;

	private UsersController(UsersDao usersDao, Result result,
			Validator validator) {
		this.usersDao = usersDao;
		this.result = result;
		this.validator = validator;
	}

	@Path("/users")
	@Post
	public void save(final User user) {
		validator.validate(user);

		validator.checking(new Validations() {
			{
				if (user.getPassword() != null
						&& user.getPasswordConfirmation() != null) {
					that(user.getPassword().equals(
							user.getPasswordConfirmation()),
							"passwordConfirmation",
							"- A Senha e a Confirmação de Senha não conferem.");
				}
			}
		});

		validator.onErrorRedirectTo(IndexController.class).index();
		user.setHashFoto(MD5Util.md5Hex(user.getEmail()));
		this.usersDao.save(user);
		result.forwardTo(LoginController.class).login(user);
	}

	@Get
	public void find(String query) {
		if (query == null) {
			return;
		}
		List<User> usuarios = usersDao.find(query);
		result.use(Results.json()).from(usuarios).exclude("password").serialize();
	}

}
