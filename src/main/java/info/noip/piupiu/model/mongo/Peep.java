package info.noip.piupiu.model.mongo;

import info.noip.piupiu.infra.MD5Util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
public class Peep implements Serializable{
	
	private static final long serialVersionUID = -319376587273659406L;

	@Id
	private ObjectId id;

	private String author;

	private Date date;

	private String text;
	
	private String hash;
	
	private HashSet<Liker> likers;

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

	public HashSet<Liker> getLikers() {
		return likers;
	}

	public void setLikers(HashSet<Liker> likers) {
		this.likers = likers;
	}

	public void addLiker(String likerEmail) {
		if(likers == null){
			likers = new HashSet<Liker>();
		}
		Liker liker = new Liker();
		liker.setUserEmail(likerEmail);
		liker.setHash(MD5Util.md5Hex(likerEmail));
		likers.add(liker);
	}
	
	public Boolean isALiker(String likerEmail){
		if(likers != null){
			Liker liker = new Liker();
			liker.setUserEmail(likerEmail);
			liker.setHash(MD5Util.md5Hex(likerEmail));
			return likers.contains(liker);
		}
		return false;
	}

	public void removeLiker(String likerEmail) {
		if(likers != null && !likers.isEmpty()){
			Liker liker = new Liker();
			liker.setUserEmail(likerEmail);
			liker.setHash(MD5Util.md5Hex(likerEmail));
			likers.remove(liker);	
		}
		
	}

}
