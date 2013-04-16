package info.noip.piupiu.controller;

import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.UserSession;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class LoginController {

	private final Result result;
	private UserSession userSession;
	private final UsersDao usersDao;

	public LoginController(Result result, UserSession userSession,
			UsersDao usersDao) {
		this.result = result;
		this.userSession = userSession;
		this.usersDao = usersDao;
	}

	@Path("/login")
	@Post
	public void login(User user) {
		User userLogged = usersDao.login(user);
		if (userLogged != null) {
			this.userSession.setUser(userLogged);
			result.redirectTo(ProfilesController.class).show(userLogged);
		} else {
			result.redirectTo(IndexController.class).index();
		}
	}

}
