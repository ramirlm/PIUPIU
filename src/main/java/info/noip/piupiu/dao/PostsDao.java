package info.noip.piupiu.dao;

import java.util.List;

import info.noip.piupiu.model.mongo.Peep;

public interface PostsDao {

	Peep save(Peep post);
	
	List<Peep> findByAuthor(String author, Integer skip, Integer limit);
}
