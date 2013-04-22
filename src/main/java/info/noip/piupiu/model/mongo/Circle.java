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
	private HashSet<String> followers;
	private HashSet<String> following;
	
	public void addFollower(String email){
		if(followers == null){
			followers = new HashSet<String>();
		}
		followers.add(email);
	}
	
	public void addFollowing(String email){
		if(following == null){
			following = new HashSet<String>();
		}
		following.add(email);
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

	public HashSet<String> getFollowers() {
		return followers;
	}

	public void setFollowers(HashSet<String> followers) {
		this.followers = followers;
	}

	public HashSet<String> getFollowing() {
		return following;
	}

	public void setFollowing(HashSet<String> following) {
		this.following = following;
	}

	public void removeFollowing(String userToUnfollowEmail) {
		if(following==null){
			following = new HashSet<String>();
		}
		following.remove(userToUnfollowEmail);
	}
	
	public void removeFollowers(String userToUnfollowEmail) {
		if(followers==null){
			followers = new HashSet<String>();
		}
		followers.remove(userToUnfollowEmail);
	}
	
}
