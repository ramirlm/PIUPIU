package info.noip.piupiu.controller;

import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.UserSession;
import info.noip.piupiu.model.mongo.Peep;

import java.util.Date;

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
	private UserSession userSession;

	public PeepsController(PostsDao postsDao, Result result, UserSession userSession) {
		this.postsDao = postsDao;
		this.result = result;
		this.userSession = userSession;
	}

	@Path("/peeps")
	@Post
	@Consumes("application/json")  
	public void save(Peep peep) {
		User user = userSession.getUser();
		if(user!=null){
			peep.setDate(new Date());
			peep.setAuthor(user.getEmail());
			postsDao.save(peep);
			result.use(Results.json()).withoutRoot().from(peep).serialize();	
		}
	}

}
