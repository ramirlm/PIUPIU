package info.noip.piupiu.controller;

import info.noip.piupiu.dao.CircleDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.infra.MD5Util;
import info.noip.piupiu.model.User;
import info.noip.piupiu.security.Public;
import info.noip.piupiu.security.UserSession;

import java.util.List;

import br.com.caelum.vraptor.Consumes;
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
	private UserSession userSession;
	private CircleDao circleDao;

	public UsersController(UsersDao usersDao, Result result,
			Validator validator, UserSession userSession, CircleDao circleDao) {
		this.usersDao = usersDao;
		this.result = result;
		this.validator = validator;
		this.userSession = userSession;
		this.circleDao = circleDao;
	}

	@Public
	@Path("/users")
	@Post
	public void save(final User user) {
		System.out.println("tesssadasdsa fdsda");
		validator.validate(user);

		validator.checking(new Validations() {
			{
				if (user.getPassword() != null	&& user.getPasswordConfirmation() != null) {
					that(user.getPassword().equals(user.getPasswordConfirmation()),	"passwordConfirmation", "- A Senha e a Confirmação de Senha não conferem.");
					System.out.println("testessssss");
				}
			}
		});

		validator.onErrorRedirectTo(LoginController.class).index();
		user.setHashFoto(MD5Util.md5Hex(user.getEmail()));
		this.usersDao.save(user);
		result.include(user);
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
	
	@Post
	@Consumes("application/json")
	public void follow(User userToFollow){
		circleDao.follow(userSession.getUser().getEmail(), userToFollow.getEmail());
		result.use(Results.status()).ok();
	}
	
	@Post
	@Consumes("application/json")
	public void unfollow(User userToUnfollow){
		circleDao.unfollow(userSession.getUser().getEmail(), userToUnfollow.getEmail());
		result.use(Results.status()).ok();
	}

}
