package info.noip.piupiu.controller;

import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.security.Public;
import info.noip.piupiu.security.UserSession;
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

	@Public
	@Get("/")
	public void index() {
	}

	@Public
	@Path("/login")
	@Post
	public void login(final User user) {
		validator.checking(new Validations() {
			{
				that(user.getEmail() != null, "login.error", "login.email.obrigatorio");
				that(user.getPassword() != null, "login.error", 	"login.password.obrigatorio");
			}
		});

		validator.onErrorRedirectTo(LoginController.class).index();

		final User userLogged = usersDao.login(user);

		validator.checking(new Validations() {
			{
				that(userLogged != null, "login.error",	"login.usuariosenha.incorretos");
			}
		});

		validator.onErrorRedirectTo(LoginController.class).index();

		this.userSession.setUser(userLogged);
		result.include("userSession", userSession);
		result.redirectTo(ProfilesController.class).show();
	}

	@Get("/logout")
	public void logout() {
		this.userSession.setUser(null);
		result.include("userSession", null);
		result.redirectTo(LoginController.class).index();
	}
}
