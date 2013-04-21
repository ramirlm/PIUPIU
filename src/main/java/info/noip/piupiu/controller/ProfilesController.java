package info.noip.piupiu.controller;

import java.util.List;

import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.mongo.Peep;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ProfilesController {

	private Result result;
	private UsersDao usersDao;
	private PostsDao postsDao;

	public ProfilesController(Result result, UsersDao usersDao, PostsDao postsDao) {
		this.result = result;
		this.usersDao = usersDao;
		this.postsDao = postsDao;
	}

	public void show(User user) {
		result.include("user", user);
	}

	@Get
	@Path("/profile/{email}")
	public void profile(String email) {
		User user = usersDao.findByEmail(email);
		List<Peep> peeps = postsDao.findByAuthor(user.getEmail(), 0, 50);
		result.include("user", user);
		result.include("peeps", peeps);
	}
}
