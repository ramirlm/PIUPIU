package info.noip.piupiu.controller;

import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.UserSession;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class LoginController {

	private final Result result;
	private UserSession userSession;
	private final UsersDao usersDao;
	private final Validator validator;

	public LoginController(Result result, UserSession userSession,
			UsersDao usersDao, Validator validator) {
		this.result = result;
		this.userSession = userSession;
		this.usersDao = usersDao;
		this.validator = validator;
	}

	@Path("/login")
	@Post
	public void login(final User user) {
		validator.checking(new Validations() {
			{
				that(user.getEmail() != null, "emailLogin",
						"- O E-mail é obrigatório.");
				that(user.getPassword() != null, "passwordLogin",
						"- A Senha é obrigatória.");
			}
		});
		
		validator.onErrorRedirectTo(IndexController.class).index();

		final User userLogged = usersDao.login(user);
		
		validator.checking(new Validations() {
			{
				that(userLogged != null, "user",
						"- Usuário e/ou senha incorretos.");
			}
		});

		validator.onErrorRedirectTo(IndexController.class).index();

		this.userSession.setUser(userLogged);
		result.include("userSession",userSession);
		result.redirectTo(ProfilesController.class).show();
	}
	
	@Get("/logout")
    public void logout() {
		this.userSession.setUser(null);
		result.include("userSession",null);
        result.redirectTo(IndexController.class).index();
    }
}
