package info.noip.piupiu.controller;

import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.model.mongo.Peep;
import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class PeepsController {

	private PostsDao postsDao;
	private Result result;

	public PeepsController(PostsDao postsDao, Result result) {
		this.postsDao = postsDao;
		this.result = result;
	}

	@Path("/peeps")
	@Post
	@Consumes("application/json")  
	public void save(Peep peep) {
		postsDao.save(peep);
		result.use(Results.json()).withoutRoot().from(peep).serialize();
	}

}
