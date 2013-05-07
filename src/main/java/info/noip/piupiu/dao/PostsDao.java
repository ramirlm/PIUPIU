package info.noip.piupiu.dao;

import java.util.List;

import info.noip.piupiu.model.User;
import info.noip.piupiu.model.mongo.Peep;

public interface PostsDao {

	Peep save(Peep post);

	List<Peep> findByAuthor(String author, Integer skip, Integer limit);

	List<Peep> retrieveTimeline(User user, Integer skip, Integer limit);

	void like(Peep peep, String likerEmail);
	
	void dislike(Peep peep, String likerEmail);
	
	Peep retrieveById(Peep peep);

	List<Peep> findByHashTag(String hashtag, Integer skip, Integer peepsLimit);
}
