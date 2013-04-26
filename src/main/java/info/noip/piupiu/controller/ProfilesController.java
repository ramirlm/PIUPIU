package info.noip.piupiu.controller;

import java.util.List;

import info.noip.piupiu.dao.CircleDao;
import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.UserSession;
import info.noip.piupiu.model.mongo.Circle;
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
	private UserSession userSession;
	private CircleDao circleDao;

	public ProfilesController(Result result, UsersDao usersDao,
			PostsDao postsDao, UserSession userSession, CircleDao circleDao) {
		this.result = result;
		this.usersDao = usersDao;
		this.postsDao = postsDao;
		this.userSession = userSession;
		this.circleDao = circleDao;
	}

	@Path("/profiles")
	public void show() {
		getNumberOfFollowersAndFollowing(userSession.getUser().getEmail());
	}

	@Get
	@Path("/profiles/{email}")
	public void profile(String email) {
		User user = usersDao.findByEmail(email);
		List<Peep> peeps = postsDao.findByAuthor(user.getEmail(), 0, 50);
		result.include("user", user);
		result.include("peeps", peeps);
		result.include("isFollowing", isFollowing(email));
		getNumberOfFollowersAndFollowing(email);
	}

	private void getNumberOfFollowersAndFollowing(String email) {
		Circle circle = circleDao.getCircleByEmail(email);

		if (circle == null) {
			result.include("followers", 0);
			result.include("following", 0);
		} else {
			result.include("followers", circle.getFollowers() == null ? 0
					: circle.getFollowers().size());
			result.include("following", circle.getFollowing() == null ? 0
					: circle.getFollowing().size());
		}
	}

	private Boolean isFollowing(String email) {
		User loggedUser = userSession.getUser();
		String loggedEmailUser = loggedUser.getEmail();
		return circleDao.isFollowing(loggedEmailUser, email);
	}

}
