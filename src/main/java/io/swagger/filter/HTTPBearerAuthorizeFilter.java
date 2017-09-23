package io.swagger.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import io.swagger.common.CacheType;
import io.swagger.model.User;
import io.swagger.service.UserService;
import io.swagger.service.common.RedisService;
import io.swagger.service.common.WebTokenService;

@WebFilter(urlPatterns = "/*", filterName = "HTTPBearerAuthorizeFilter",
initParams={@WebInitParam(name="exceptionConfiguration",value="user/create|user/login|verifyCode/get|api-docs|user/getIdentifyCode/\\d+|user/\\d+/reset|topic/invite/accept")})
@Order(Integer.MAX_VALUE)
public class HTTPBearerAuthorizeFilter implements Filter {
	
	protected List<Pattern> patterns = new ArrayList<Pattern>();
	
	@Autowired 
	private WebTokenService webTokenService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private UserService userService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest)request;  
		
		String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
	    if (url.startsWith("/") && url.length() > 1) {
	      url = url.substring(1);
	    }
	 
	    if (isInclude(url)){
	    	chain.doFilter(request, response);  
	      return;
	    } 
		
        String auth = httpRequest.getHeader("Authorization");
        if(auth==null){
        	auth = httpRequest.getParameter("Authorization");
        }
        if ((auth != null) && (auth.length() > 7))  
        {  
            String HeadStr = auth.substring(0, 6).toLowerCase();  
            if (HeadStr.compareTo("bearer") == 0)  
            {  
                auth = auth.substring(7, auth.length());
                String md5key = redisService.getMD5CacheKey(CacheType.tokenKey, auth);
               
                String currentUserId = redisService.get(md5key);
                if(currentUserId == null){
                	currentUserId = webTokenService.parseAccessTokenToUserId(auth);
                }
                if (currentUserId != null && userService.getUserByUserid(currentUserId)!=null)  
                {  
                	redisService.set(md5key, currentUserId,860000);
                    chain.doFilter(request, response);  
                    return;  
                }  
                /*User currentUser = redisService.get(md5key);
                if(currentUser == null){
                	currentUser = webTokenService.parseAccessToken(auth);
                }
                if (currentUser != null)  
                {  
                	redisService.setObjct(md5key, currentUser,860000);
                    chain.doFilter(request, response);  
                    return;  
                }  */
            }  
        }  
        
        HttpServletResponse httpResponse = (HttpServletResponse) response;  
        httpResponse.setCharacterEncoding("UTF-8");    
        httpResponse.setContentType("application/json; charset=utf-8");   
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  
  
        return;  
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,  
                filterConfig.getServletContext()); 
		String exceptionConfiguration = filterConfig.getInitParameter("exceptionConfiguration");
		String[] exceptionArray = StringUtils.tokenizeToStringArray(exceptionConfiguration, "|");
		for(String exception : exceptionArray){
			patterns.add(Pattern.compile(exception));
		}
		
	}
	
	
	 /**
	   * 是否需要过滤
	   * @param url
	   * @return
	   */
	  private boolean isInclude(String url) {
	    for (Pattern pattern : patterns) {
	      Matcher matcher = pattern.matcher(url);
	      if (matcher.matches()) {
	        return true;
	      }
	    }
	    return false;
	  }

}
