package info.noip.piupiu.dao;

public interface CircleDao {
	
	void follow(String userEmail, String userToFollowEmail);

	void unfollow(String userEmail, String userToUnfollowEmail);
	
	Boolean isFollowing(String loggedEmailUser, String email);
	
}
