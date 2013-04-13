package info.noip.piupiu.controller;


import static info.noip.piupiu.validation.CustomMatchers.notEmpty;
import static org.hamcrest.Matchers.is;
import info.noip.piupiu.dao.UsersDao;
import info.noip.piupiu.model.User;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class UsersController {

	private final UsersDao usersDao;
	private final Validator validator;
	private final Result result;

	private UsersController(UsersDao usersDao, Result result, Validator validator) {
		this.usersDao = usersDao;
		this.result = result;
		this.validator = validator;
	}

	@Path("/users")
	@Post
	public void save(final User user) {
		validator.checking(new Validations() {{
	    	if (user != null) {
	    		that(user.getName(), is(notEmpty()), "name", "Nome é obrigatório");
	    		that(user.getPassword(), is(notEmpty()), "password", "Senha é obrigatória");
	    		that(user.getEmail(), is(notEmpty()),"email", "Email é obrigatório");
	    	}
		}});
		
		validator.onErrorRedirectTo(IndexController.class).index();
		
		this.usersDao.save(user);
		result.forwardTo(LoginController.class).login(user);
	}

}
