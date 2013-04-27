package info.noip.piupiu.model.mongo;

import java.util.HashSet;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "circle")
public class Circle {

	@Id
	private ObjectId id;
	private String userEmail;
	private HashSet<Avatar> followers;
	private HashSet<Avatar> following;

	public void addFollower(String email) {
		if (followers == null) {
			followers = new HashSet<Avatar>();
		}
		Avatar follower = new Avatar(email);
		followers.add(follower);
	}

	public void addFollowing(String email) {
		if (following == null) {
			following = new HashSet<Avatar>();
		}
		Avatar follow = new Avatar(email);
		following.add(follow);
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public HashSet<Avatar> getFollowers() {
		return followers;
	}

	public void setFollowers(HashSet<Avatar> followers) {
		this.followers = followers;
	}

	public HashSet<Avatar> getFollowing() {
		return following;
	}

	public void setFollowing(HashSet<Avatar> following) {
		this.following = following;
	}

	public void removeFollowing(String emailUserToUnfollow) {
		if (following == null) {
			following = new HashSet<Avatar>();
		}
		Avatar userToUnfollow = new Avatar(emailUserToUnfollow);
		following.remove(userToUnfollow);
	}

	public void removeFollowers(String emailUserToUnfollow) {
		if (followers == null) {
			followers = new HashSet<Avatar>();
		}
		Avatar userToUnfollow = new Avatar(emailUserToUnfollow);
		followers.remove(userToUnfollow);
	}

}
