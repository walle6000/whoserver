package io.swagger.utils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.swagger.common.CacheType;
import io.swagger.model.User;
import io.swagger.service.UserService;
import io.swagger.service.common.RedisService;

@Component
public class UserUtil {
	
	@Resource
    private RedisService redisServiceAutowired;

    private static RedisService redisService;
    
    @Resource
    private UserService userServiceAutowired;
    
    private static UserService userService;
    
    @PostConstruct
    public void init() {
    	redisService = this.redisServiceAutowired;
    	userService  = this.userServiceAutowired;
    }
	
    public static User getCurrentLoginUserInfo(){
    	 HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
    	 User currentUser = null;
    	 String auth = request.getHeader("Authorization");
    	 if(auth==null){
         	auth = request.getParameter("Authorization");
         }
         if ((auth != null) && (auth.length() > 7)){  
             String HeadStr = auth.substring(0, 6).toLowerCase();  
             if (HeadStr.compareTo("bearer") == 0){  
                 auth = auth.substring(7, auth.length());
                 String md5key = redisService.getMD5CacheKey(CacheType.tokenKey, auth);
                 String currentUserId = redisService.get(md5key);
                 if(currentUserId!=null){
                	 currentUser = userService.getUserByUserid(currentUserId);
                 }
             }  
         }
         return currentUser;
     }
    
    public static void clearCurrentLoginUserCache(){
    	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
   	    String auth = request.getHeader("Authorization");  
        if ((auth != null) && (auth.length() > 7)){  
            String HeadStr = auth.substring(0, 6).toLowerCase();  
            if (HeadStr.compareTo("bearer") == 0){  
                auth = auth.substring(7, auth.length());
                String md5key = redisService.getMD5CacheKey(CacheType.tokenKey, auth);
                redisService.remove(md5key);
            }  
        }
    }
}
