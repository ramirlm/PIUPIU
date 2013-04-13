package info.noip.piupiu.controller;

import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class UsersController {

	private final UsersDao usersDao;
	private final Result result;

	private UsersController(UsersDao usersDao, Result result) {
		this.usersDao = usersDao;
		this.result = result;
	}

	@Path("/users")
	@Post
	public void save(final User user) {
		this.usersDao.save(user);
		result.include("notice", "User " + user.getName()
				+ " successfully added");
	}

}
