package io.swagger.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties(prefix = "sms", locations = "classpath:sms.properties")
public class SMSService {
	
	private Logger logger = LoggerFactory.getLogger(SMSService.class);
	
	private String defaultverifyCodeTemplate;
	
	private String userCreatingVerifyCodeTemplate;
	
	private String userResetVerifyCodeTemplate;

	public boolean sendSMSVerifyCode(String SMSverifyCode,String phoneNumber){
		String verifyCodeContent = String.format(defaultverifyCodeTemplate, SMSverifyCode);
		logger.info("SMSService - sendSMSVerifyCode SMSverifyCode:"+SMSverifyCode);
		logger.info("SMSService - sendSMSVerifyCode verifyCodeContent:"+verifyCodeContent);
		logger.info("SMSService - sendSMSVerifyCode phoneNumber:"+phoneNumber);
		//调用短信发送接口(待定)
		return true;
	}
	
	public boolean sendSMSVerifyCode(String SMSverifyCode,String phoneNumber,Integer messageType){
		String verifyCodeContent = null;
		switch(messageType)	{
		case 0:
			verifyCodeContent = String.format(userCreatingVerifyCodeTemplate, SMSverifyCode);
			break;
		case 1:
			verifyCodeContent = String.format(userResetVerifyCodeTemplate, SMSverifyCode);
			break;
		default:
			verifyCodeContent = String.format(defaultverifyCodeTemplate, SMSverifyCode);
		}
		logger.info("SMSService - sendSMSVerifyCode SMSverifyCode:"+SMSverifyCode);
		logger.info("SMSService - sendSMSVerifyCode verifyCodeContent:"+verifyCodeContent);
		logger.info("SMSService - sendSMSVerifyCode phoneNumber:"+phoneNumber);
		//调用短信发送接口(待定)
		return true;
	}

	public String getDefaultverifyCodeTemplate() {
		return defaultverifyCodeTemplate;
	}

	public void setDefaultverifyCodeTemplate(String defaultverifyCodeTemplate) {
		this.defaultverifyCodeTemplate = defaultverifyCodeTemplate;
	}

	public String getUserCreatingVerifyCodeTemplate() {
		return userCreatingVerifyCodeTemplate;
	}

	public void setUserCreatingVerifyCodeTemplate(String userCreatingVerifyCodeTemplate) {
		this.userCreatingVerifyCodeTemplate = userCreatingVerifyCodeTemplate;
	}

	public String getUserResetVerifyCodeTemplate() {
		return userResetVerifyCodeTemplate;
	}

	public void setUserResetVerifyCodeTemplate(String userResetVerifyCodeTemplate) {
		this.userResetVerifyCodeTemplate = userResetVerifyCodeTemplate;
	}

	

	
	
}
