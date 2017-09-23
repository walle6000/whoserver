package io.swagger.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.common.CacheType;
import io.swagger.service.common.RedisService;
import io.swagger.utils.VerifyCodeUtils;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/verifyCode/get", description="获取验证码图片")
public class VerifyCodeServlet extends HttpServlet {
	
	private Logger logger = LoggerFactory.getLogger(VerifyCodeServlet.class);
	
	@Autowired
	private RedisService redisService;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("VerifyCodeServlet - doGet start to get VerifyCode img");
		resp.setHeader("Pragma", "No-cache"); 
		resp.setHeader("Cache-Control", "no-cache"); 
		resp.setDateHeader("Expires", 0); 
		resp.setContentType("image/jpeg"); 
        //生成随机字串 
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
        logger.info("VerifyCodeServlet - doGet VerifyCode:" + verifyCode);
        //存入会话session 
        HttpSession session = req.getSession(true); 
        String sessionId = session.getId();
        logger.info("VerifyCodeServlet - doGet sessionId:" + sessionId);
        logger.info("VerifyCodeServlet - doGet key:" + redisService.getMD5CacheKey(CacheType.verfifyCode, sessionId));
        redisService.set(redisService.getMD5CacheKey(CacheType.verfifyCode, sessionId), verifyCode, 60);
        //生成图片 
        int w = 100, h = 30; 
        VerifyCodeUtils.outputImage(w, h, resp.getOutputStream(), verifyCode);  
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	logger.info("VerifyCodeServlet - doPost start to get VerifyCode img");
    	doGet(req,resp);
    }
}
