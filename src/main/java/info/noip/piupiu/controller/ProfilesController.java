package info.noip.piupiu.controller;

import info.noip.piupiu.dao.CircleDao;
import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.UserSession;
import info.noip.piupiu.model.mongo.Avatar;
import info.noip.piupiu.model.mongo.Circle;
import info.noip.piupiu.model.mongo.Peep;

import java.util.ArrayList;
import java.util.List;

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
		getNumberOfFollowersAndFollowing2(userSession.getUser().getEmail());
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

	private void getNumberOfFollowersAndFollowing2(String email) {
		List<Avatar> listaVazia = new ArrayList<Avatar>();

		Circle circle = circleDao.getCircleByEmail(email);
		if (circle == null) {
			result.include("followers", listaVazia);
			result.include("following", listaVazia);
		} else {
			if (circle.getFollowers() != null) {
				result.include("followers", new ArrayList<Avatar>(circle.getFollowers()));
			} else {
				result.include("followers", listaVazia);
			}

			if (circle.getFollowing() != null) {
				result.include("following", new ArrayList<Avatar>(circle.getFollowing()));
			} else {
				result.include("following", listaVazia);
			}
		}
	}

	private Boolean isFollowing(String email) {
		User loggedUser = userSession.getUser();
		String loggedEmailUser = loggedUser.getEmail();
		return circleDao.isFollowing(loggedEmailUser, email);
	}

}
