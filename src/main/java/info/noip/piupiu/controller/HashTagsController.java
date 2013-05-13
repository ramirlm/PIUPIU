package info.noip.piupiu.controller;

import info.noip.piupiu.dao.CircleDao;
import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.mongo.Avatar;
import info.noip.piupiu.model.mongo.Circle;
import info.noip.piupiu.model.mongo.Peep;
import info.noip.piupiu.security.UserSession;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class HashTagsController {

	private Result result;
	private UsersDao usersDao;
	private PostsDao postsDao;
	private UserSession userSession;
	private CircleDao circleDao;

	public HashTagsController(Result result, UsersDao usersDao,
			PostsDao postsDao, UserSession userSession, CircleDao circleDao) {
		this.result = result;
		this.usersDao = usersDao;
		this.postsDao = postsDao;
		this.userSession = userSession;
		this.circleDao = circleDao;
	}

	@Path("/hashtags/{hashtag}")
	public void show(String hashtag) {
		result.include("user", userSession.getUser());
		result.include("hashtag", hashtag);
		getFollowersAndFollowing(userSession.getUser().getEmail());
	}

	@Get
	@Path("/hashtags/{hashtag}/skip/{skip}")
	public void search(String hashtag, Integer skip) {
		List<Peep> peeps = postsDao.findByHashTag(hashtag, skip, PeepsController.PEEPS_LIMIT);
		result.include("peeps", peeps);
		result.forwardTo(HashTagsController.class).result();
	}
	
	public void result(){ }

	public void getFollowersAndFollowing(String email) {
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

}
