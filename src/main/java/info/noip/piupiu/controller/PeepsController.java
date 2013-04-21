package info.noip.piupiu.controller;

import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.UserSession;
import info.noip.piupiu.model.mongo.Peep;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class PeepsController {

	private PostsDao postsDao;
	private UsersDao usersDao;
	private Result result;
	private UserSession userSession;

	public PeepsController(PostsDao postsDao, UsersDao usersDao,Result result,
			UserSession userSession) {
		this.postsDao = postsDao;
		this.usersDao = usersDao;
		this.result = result;
		this.userSession = userSession;
	}

	@Path("/peeps")
	@Post
	@Consumes("application/json")
	public void save(Peep peep) {
		User user = userSession.getUser();
		if (user != null) {
			peep.setDate(new Date());
			peep.setAuthor(user.getEmail());
			postsDao.save(peep);
			result.include("peep", peep);
		}
	}

	@Path("/peeps/{skip}/{limit}")
	@Get
	public void list(Integer skip, Integer limit) {
		User user = userSession.getUser();
		List<Peep> peeps = postsDao.findByAuthor(user.getEmail(), skip, limit);
		result.use(Results.json()).withoutRoot().from(peeps).serialize();
	}

	@Path("/peeps/show")
	@Get
	public void show() {
		User user = userSession.getUser();
		List<Peep> peeps = postsDao.findByAuthor(user.getEmail(), 0, 50);
		result.include("peeps", peeps);
	}
	
}
