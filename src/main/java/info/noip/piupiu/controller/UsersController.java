package info.noip.piupiu.controller;

import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class UsersController {

	private final UsersDao usersDao;
	private final Validator validator;
	private final Result result;

	private UsersController(UsersDao usersDao, Result result, Validator validator) {
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

		this.usersDao.save(user);
		result.forwardTo(LoginController.class).login(user);
	}

}
