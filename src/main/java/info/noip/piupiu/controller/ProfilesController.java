package info.noip.piupiu.controller;

import info.noip.piupiu.dao.CircleDao;
import info.noip.piupiu.dao.PostsDao;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import info.noip.piupiu.model.mongo.Avatar;
import info.noip.piupiu.model.mongo.Circle;
import info.noip.piupiu.model.mongo.Peep;
import info.noip.piupiu.security.UserSession;
import info.noip.piupiu.service.UploadImage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

@Resource
public class ProfilesController {

	private Result result;
	private UsersDao usersDao;
	private PostsDao postsDao;
	private UserSession userSession;
	private CircleDao circleDao;
	private final Validator validator;

	public ProfilesController(Result result, UsersDao usersDao,
			PostsDao postsDao, UserSession userSession, CircleDao circleDao, Validator validator) {
		this.result = result;
		this.usersDao = usersDao;
		this.postsDao = postsDao;
		this.userSession = userSession;
		this.circleDao = circleDao;
		this.validator = validator;
	}

	@Path("/profiles")
	public void show() {
		getFollowersAndFollowing(userSession.getUser().getEmail());
	}
	
	@Post("/profiles/uploadImage")
	public void uploadImage(String message, UploadedFile photo) throws FileNotFoundException, IOException{
		if(photo!=null){
			String imageLink = new UploadImage().attach(photo.getFile()).send();	
			User user = userSession.getUser();
			if (user != null) {
				Peep peep = new Peep();
				peep.setText(message);
				peep.setDate(new Date());
				peep.setAuthor(user.getEmail());
				peep.setHash(user.getHashFoto());
				peep.addHashTags();
				peep.setImageLink(imageLink);
				postsDao.save(peep);
			}
		}
		validator.onErrorRedirectTo(ProfilesController.class).show();
		result.redirectTo(ProfilesController.class).show();
	}

	@Get
	@Path("/profiles/{email}")
	public void profile(String email) {
		User user = usersDao.findByEmail(email);
		result.include("user", user);
		result.include("isFollowing", isFollowing(email));
		getFollowersAndFollowing(email);
	}
	
	@Get
	@Path("/profiles/peeps/{email}")
	public void profilePeeps(String email) {
		User user = usersDao.findByEmail(email);
		List<Peep> peeps = postsDao.findByAuthor(user.getEmail(), 0, PeepsController.PEEPS_LIMIT);
		result.include("peeps", peeps);
		result.forwardTo(ProfilesController.class).peeps();
	}
	
	@Get
	@Path("/profiles/peeps/{email}/skip/{skip}")
	public void profilePeeps(String email, Integer skip) {
		User user = usersDao.findByEmail(email);
		List<Peep> peeps = postsDao.findByAuthor(user.getEmail(), skip, PeepsController.PEEPS_LIMIT);
		result.include("peeps", peeps);
		result.forwardTo(ProfilesController.class).peeps();
	}
	
	/** 
	 *  Nao remover. Metodo responsavel por redirecionar 
	 * 	as duas chamdas feitas ao metodos profilePeeps 
	 *  para a pagina 'peeps'
	 */
	public void peeps(){ }

	public void getFollowersAndFollowing(String email) {
		List<Avatar> listaVazia = new ArrayList<Avatar>();

		Circle circle = circleDao.getCircleByEmail(email);
		if (circle == null) {
			result.include("followers", listaVazia);
			result.include("following", listaVazia);
		} else {
			if (circle.getFollowers() != null) {
				result.include("followers", new ArrayList<Avatar>(circle.getFollowers()));
			} else {
				result.include("followers", listaVazia);
			}

			if (circle.getFollowing() != null) {
				result.include("following", new ArrayList<Avatar>(circle.getFollowing()));
			} else {
				result.include("following", listaVazia);
			}
		}
	}
	
	@Path("/profiles/listAll/{userMail}/{followersOrFollowing}")
	public void listAll(String userMail,String followersOrFollowing){
		List<Avatar> listaVazia = new ArrayList<Avatar>();
		
		Circle circle = circleDao.getCircleByEmail(userMail);
		
		if (circle == null) {
			result.include("listAll", listaVazia);
			result.include("listAll", listaVazia);
		} else {
			if(followersOrFollowing.equals("followers"))
			{
				if (circle.getFollowers() != null) {
					result.include("listAll", new ArrayList<Avatar>(circle.getFollowers()));
				} else {
					result.include("listAll", listaVazia);
				}
			}else{
				if (circle.getFollowing() != null) {
					result.include("listAll", new ArrayList<Avatar>(circle.getFollowing()));
				} else {
					result.include("listAll", listaVazia);
				}
			}
			
		}
	}
	
	private Boolean isFollowing(String email) {
		User loggedUser = userSession.getUser();
		String loggedEmailUser = loggedUser.getEmail();
		return circleDao.isFollowing(loggedEmailUser, email);
	}
	

}
