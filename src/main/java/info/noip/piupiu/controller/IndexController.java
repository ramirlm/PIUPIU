package info.noip.piupiu.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class IndexController {

	@Get
	@Path("/index")
	public void index(){
	}
	
}
