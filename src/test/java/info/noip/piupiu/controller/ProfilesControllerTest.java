/**
 *
 */
package info.noip.piupiu.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import info.noip.piupiu.dao.CircleDao;
import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.mongo.Avatar;
import info.noip.piupiu.model.mongo.Circle;
import info.noip.piupiu.security.UserSession;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.util.test.MockResult;

/**
 * @author efreitas
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class ProfilesControllerTest {

	@Mock
	PostsDao postsDao;
	@Mock
	UsersDao usersDao;
	@Mock
	UserSession userSession;
	@Mock
	CircleDao circleDao;
	@Mock
	Validator validator;

	@Test
	public void shouldShowFollowersAndFollowing(){

		List<Avatar> followers;
		List<Avatar> following;
		Circle userCircle = new Circle();

		MockResult result = new MockResult();

		ProfilesController controller = new ProfilesController(result, usersDao, postsDao, userSession, circleDao, validator);

		when(circleDao.getCircleByEmail("andersonsilva@ufc.com")).thenReturn(null);
		controller.getFollowersAndFollowing("andersonsilva@ufc.com");
		following = (List<Avatar>) result.included().get("following");
		followers = (List<Avatar>) result.included().get("followers");
		Assert.assertEquals(0,following.size());
		Assert.assertEquals(0,followers.size());

		userCircle.addFollower("chaelsonnen@gmail.com");
		when(circleDao.getCircleByEmail("andersonsilva@ufc.com")).thenReturn(userCircle);
		controller.getFollowersAndFollowing("andersonsilva@ufc.com");
		following = (List<Avatar>) result.included().get("following");
		followers = (List<Avatar>) result.included().get("followers");
		Assert.assertEquals(0,following.size());
		Assert.assertEquals(1,followers.size());

		userCircle.setFollowers(null);
		userCircle.addFollowing("jonjones@ufc.com");
		when(circleDao.getCircleByEmail("andersonsilva@ufc.com")).thenReturn(userCircle);
		controller.getFollowersAndFollowing("andersonsilva@ufc.com");
		following = (List<Avatar>) result.included().get("following");
		followers = (List<Avatar>) result.included().get("followers");
		Assert.assertEquals(1,following.size());
		Assert.assertEquals(0,followers.size());
	}

}
