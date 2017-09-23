package io.swagger.common;

public interface GlobalConstants {
	/**
	 * 最大消息数
	 */
	static final Integer MAX_MESSAGE_NUM = 100;
	
	/**
	 * 缓存失效时间，以秒计
	 */
	static final int CACHE_TIMEOUT = 25 * 24 * 60 * 60;
	
	/**
	 * 用户状态超时时间
	 */
	static final int CACHE_USER_STATUS_TIMEOUT = 2 * 24 * 60 * 60;
	
	/**
	 * 缓存短期失效时间，以秒计
	 */
	static final int CACHE_SHORT_TIMEOUT = 60;
	
	/**
	 * 短信验证码生成源
	 */
	static final String SMS_VERIFY_SOURCE_CODE = "0123456789";
	
	/**
	 * 短信验证码失效时间
	 */
	static final int SMS_VERIFY_CODE_TIMEOUT = 60*10;
}
