package info.noip.piupiu.dao;

import info.noip.piupiu.model.mongo.Circle;

public interface CircleDao {
	
	void follow(String userEmail, String userToFollowEmail);

	void unfollow(String userEmail, String userToUnfollowEmail);
	
	Boolean isFollowing(String loggedEmailUser, String email);

	Circle getCircleByEmail(String email);
	
}
