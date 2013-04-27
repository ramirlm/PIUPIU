package info.noip.piupiu.controller;

import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.UserSession;
import info.noip.piupiu.model.mongo.Liker;
import info.noip.piupiu.model.mongo.Peep;

import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class PeepsController {

	private static final Integer PEEPS_LIMIT = 20;
	
	private PostsDao postsDao;
	private Result result;
	private UserSession userSession;

	public PeepsController(PostsDao postsDao, Result result,
			UserSession userSession) {
		this.postsDao = postsDao;
		this.result = result;
		this.userSession = userSession;
	}

	@Path("/peeps")
	@Post
	@Consumes("application/json")
	public void save(Peep peep) {
		User user = userSession.getUser();
		if (user != null) {
			peep.setDate(new Date());
			peep.setAuthor(user.getEmail());
			peep.setHash(user.getHashFoto());
			postsDao.save(peep);
			result.include("peep", peep);
		}
	}

	@Path("/peeps/{skip}")
	@Get
	public void list(Integer skip) {
		User user = userSession.getUser();
		List<Peep> peeps = postsDao.retrieveTimeline(user, skip, PEEPS_LIMIT);
		result.include("peeps", peeps);
		result.forwardTo(PeepsController.class).show();
	}

	@Path("/peeps/show")
	@Get
	public void list(){
		User user = userSession.getUser();
		List<Peep> peeps = postsDao.retrieveTimeline(user, 0, PEEPS_LIMIT);
		result.include("peeps", peeps);		
		result.forwardTo(PeepsController.class).show();
	}

	/** 
	 *  Nao remover. Metodo responsavel por redirecionar 
	 * 	as duas chamdas feitas ao metodos list() e list(Integer skip, Integer limit) 
	 *  para a pagia=na 'show'
	 */
	public void show() {}

	@Path("/peeps/like")
	@Post
	@Consumes("application/json")
	public void like(Peep peep){
		User loggedUser = userSession.getUser();
		postsDao.like(peep, loggedUser.getEmail());
		result.use(Results.status()).ok();
	}
	
	@Path("/peeps/showLikers")
	@Post
	@Consumes("application/json")
	public void showLikers(Peep peep){
		peep = postsDao.retrieveById(peep);
		result.include("isALiker", peep.isALiker(userSession.getUser().getEmail()));
		result.include("peep", peep);
	}
	
	@Path("/peeps/dislike")
	@Post
	@Consumes("application/json")
	public void dislike(Peep peep){
		User loggedUser = userSession.getUser();
		postsDao.dislike(peep, loggedUser.getEmail());
		result.use(Results.status()).ok();
	}
}
