package info.noip.piupiu.model.mongo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Peep implements Serializable {

	private static final long serialVersionUID = -319376587273659406L;

	@Id
	private ObjectId id;

	private String author;

	private Date date;

	private String text;

	private String hash;

	private HashSet<Avatar> likers;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public HashSet<Avatar> getLikers() {
		return likers;
	}

	public void setLikers(HashSet<Avatar> likers) {
		this.likers = likers;
	}

	public void addLiker(String likerEmail) {
		if (likers == null) {
			likers = new HashSet<Avatar>();
		}
		Avatar liker = new Avatar(likerEmail);
		likers.add(liker);
	}

	public Boolean isALiker(String likerEmail) {
		if (likers != null) {
			Avatar liker = new Avatar(likerEmail);
			return likers.contains(liker);
		}
		return false;
	}

	public void removeLiker(String likerEmail) {
		if (likers != null && !likers.isEmpty()) {
			Avatar liker = new Avatar(likerEmail);
			likers.remove(liker);
		}

	}

}
