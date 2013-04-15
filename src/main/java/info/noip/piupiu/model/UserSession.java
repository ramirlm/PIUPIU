package info.noip.piupiu.model;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import info.noip.piupiu.model.User;


@Component
@SessionScoped
public class UserSession {

    private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
