package info.noip.piupiu.security;

import info.noip.piupiu.model.User;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;


@Component
@SessionScoped
public class UserSession implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLogged() {
        return user != null;
    }

    public void logout() {
        user = null;
    }

}
