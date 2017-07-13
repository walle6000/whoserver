package io.swagger.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "audience", locations = "classpath:jwt.properties")
public class AudienceService {
	private String clientId;  
    private String base64Secret;  
    private String name;  
    private long expiresSecond;  
    public String getClientId() {  
        return clientId;  
    }  
    public void setClientId(String clientId) {  
        this.clientId = clientId;  
    }  
    public String getBase64Secret() {  
        return base64Secret;  
    }  
    public void setBase64Secret(String base64Secret) {  
        this.base64Secret = base64Secret;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public long getExpiresSecond() {  
        return expiresSecond;  
    }  
    public void setExpiresSecond(long expiresSecond) {  
        this.expiresSecond = expiresSecond;  
    }  
	
}
