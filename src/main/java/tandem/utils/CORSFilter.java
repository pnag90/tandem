package tandem.utils;

import java.io.IOException;

//import org.springframework.stereotype.Component;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Implements a filter for REST connections
 * Avoids Access-Control-Allow-Origin errors on the client
 */

//@Component
public class CORSFilter implements Filter {
	private static final String ORIGIN_HEADER = "origin";

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {       
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		String origin = request.getHeader(ORIGIN_HEADER);

		response.setHeader("Access-Control-Allow-Origin",origin);
		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		//response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		//response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		//response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		chain.doFilter(req, resp);
	}	

	@Override
	public void destroy() {}
}