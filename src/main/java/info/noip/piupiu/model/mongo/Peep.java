package info.noip.piupiu.model.mongo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	private String formattedText;

	private String hash;

	private HashSet<Avatar> likers;

	private HashSet<String> hashTags;

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

	public HashSet<String> getHashTags() {
		if (hashTags == null) {
			hashTags = new HashSet<String>();
		}
		return hashTags;
	}

	public String getFormattedText() {
		if (formattedText == null)
			formattedText = formatText();
		return formattedText;
	}

	public void setHashTags(HashSet<String> hashTags) {
		this.hashTags = hashTags;
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

	public void addHashTags() {
		if (this.text != null) {
			Pattern pattern = Pattern.compile("\\B#(\\w*[A-Za-z_]+\\w*)");
			Matcher matcher = pattern.matcher(this.text);

			while (matcher.find()) {
				String attribute = matcher.group();
				getHashTags().add(attribute);
			}
		}
	}

	public String formatText() {
		String ret = "";
		if (this.text != null) {
			ret = this.text;
			Pattern pattern = Pattern.compile("\\B#(\\w*[A-Za-z_]+\\w*)");
			Matcher matcher = pattern.matcher(ret);

			while (matcher.find()) {
				String attribute = matcher.group();
				String serchableAttribute = attribute.substring(1,
						attribute.length());
				ret = ret.replaceAll(attribute, "<a href='/piupiu/hashtags/"
						+ serchableAttribute + "'>" + attribute + "</a>");
			}
			
			String urlPattern = "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)";
			Pattern re = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			Matcher m = re.matcher(ret);
			while (m.find()) {
				String attribute = m.group();
				ret = ret.replaceAll(attribute, "<a href='" + attribute + "' target='_blank'>" + attribute + "</a>");
			}
		}

		return ret;
	}

}
