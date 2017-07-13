package io.swagger.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import io.swagger.service.RedisService;
import io.swagger.service.WebTokenService;

public class HTTPBearerAuthorizeFilter implements Filter {
	
	@Autowired 
	private WebTokenService webTokenService;
	
	@Autowired
	private RedisService redisService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest)request;  
        String auth = httpRequest.getHeader("Authorization");  
        if ((auth != null) && (auth.length() > 7))  
        {  
            String HeadStr = auth.substring(0, 6).toLowerCase();  
            if (HeadStr.compareTo("bearer") == 0)  
            {  
                  
                auth = auth.substring(7, auth.length());   
                if (webTokenService.parseAccessToken(auth) != null)  
                {  
                    chain.doFilter(request, response);  
                    return;  
                }  
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
	}

}
