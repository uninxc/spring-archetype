package ${package}.web;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CommonController implements ErrorController {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(CommonController.class);
	private static final String ERRPATH = "/error";
	
	@RequestMapping(value = ERRPATH)
    public String error() {
        return "error";
    }

	@Override
	public String getErrorPath() {
		 return ERRPATH;
	}


	
}