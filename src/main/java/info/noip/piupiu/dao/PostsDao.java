package info.noip.piupiu.dao;

import info.noip.piupiu.model.mongo.Peep;

public interface PostsDao {

	Peep save(Peep post);
	
}
