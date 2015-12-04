package tandem.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tandem.model.Users;

import java.util.Date;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/security/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @RequestMapping (value={"/teste"},  method = { RequestMethod.POST, RequestMethod.GET } )
	public @ResponseBody Date getData() {
		Date d = new Date();
		System.out.println("Estou a usar o cenas...");
		return d;
	}

    @RequestMapping(value = {"/auth"}, method = RequestMethod.POST)
    @ResponseBody
    public Users auth(@RequestBody TreeMap<String, Object> credentials, HttpServletResponse response) throws Exception {
        // simulate delay to test UI loader/spinner
        Thread.sleep(2000);
        logger.info("inside login - user: " + credentials);
        
        if (!credentials.get("username").toString().isEmpty() && credentials.get("password").toString().isEmpty()) {
            return new Users("abc", "1234567890");
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return new Users();
    }
}