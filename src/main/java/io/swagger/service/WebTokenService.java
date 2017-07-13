package io.swagger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.swagger.model.AccessToken;
import io.swagger.model.User;
import io.swagger.utils.JWTUtil;

@Service
public class WebTokenService {
	
	@Autowired
	private AudienceService audienceService;
	
	@Autowired
	private UserService userService;
	
	public AccessToken getAccessToken(User user){
		//拼装accessToken  
       String accessToken = JWTUtil.createJWT(String.valueOf(user.getId()),
    		                                  user.getUserid(),  
                                              user.getRole(), 
                                              audienceService.getClientId(), 
                                              audienceService.getName(),  
                                              audienceService.getExpiresSecond() * 1000, 
                                              audienceService.getBase64Secret()); 
          
        //返回accessToken  
       AccessToken accessTokenEntity = new AccessToken();  
       accessTokenEntity.setAccess_token(accessToken);  
       accessTokenEntity.setExpires_in(audienceService.getExpiresSecond());  
       accessTokenEntity.setToken_type("bearer");  
        
        return accessTokenEntity;
	}
	
	public User parseAccessToken(String token){
		Claims claims = JWTUtil.parseJWT(token, audienceService.getBase64Secret());
		if(claims != null){
			String userid = (String) claims.get("userid");
			User user = userService.getUserByUserid(userid);
			return user;
		}
		return null;
	}

}
