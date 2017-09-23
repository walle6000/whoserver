package io.swagger.serviceTest;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.swagger.Swagger2SpringBoot;
import io.swagger.dao.UserDao;
import io.swagger.dao.UserFriendDao;
import io.swagger.model.User;
import io.swagger.model.UserFriend;
import io.swagger.model.UserProfile;
import io.swagger.service.UserService;
import io.swagger.service.common.RedisService;
import io.swagger.utils.PinYinUtil;
import io.swagger.utils.QRCodeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Swagger2SpringBoot.class)
@WebAppConfiguration
public class UserServiceTest {

	@Autowired
	UserService userService;
	@Autowired
	UserFriendDao userFriendDao;
	
	private String[] lastName = {"李","王","张","刘","陈","杨","赵","黄","周","吴"};
	
	private String account = "13219054487";
	
	@Test
	public void testSaveUser() {
		Random random = new Random();
	   for(int i = 0; i < 20; i++){
		   int nameIndex = random.nextInt(lastName.length);
		   User mockUser = new User();
		   mockUser.setUserid(13590000000L+i+"");
		   mockUser.setPassword(DigestUtils.md5Hex("1"));
		   mockUser.setUserStatus(2);
		   
		   UserProfile userProfile = new UserProfile();
		   userProfile.userName(lastName[nameIndex]+"姓机器人"+i);
		   userProfile.setGender(i%2==0?"m":"f");
		   userProfile.setHeadIcon("../../image/login/man.png");
		   userProfile.setThumbnailheadIcon("../../image/login/man.png");
		   userProfile.setUserPinYin(PinYinUtil.getPinYin(userProfile.getUserName()));
		   userProfile.setEmail("robot@email.com");
		   userProfile.setSummry("测试专用机器人");
		   ByteArrayOutputStream out = new ByteArrayOutputStream();
		   QRCodeUtil.QREncode(mockUser.getUserid(), 100, out);
		   userProfile.setQrCode(out.toByteArray());
		   mockUser.setUserProfile(userProfile);
		   userService.saveUser(mockUser);
		   
		   
		    UserFriend friend = new UserFriend();
			friend.setUserId(account);
			friend.setFriendId(mockUser.getUserid());
			friend.setNickName(userProfile.getUserName());
			friend.setNickNamePinYin(userProfile.getUserPinYin());
			friend.setStatus(1);
			friend.setFriendOrigin(1);
			userFriendDao.saveAndFlush(friend);
			
			UserFriend revertfriend = new UserFriend();
			revertfriend.setUserId(mockUser.getUserid());
			revertfriend.setFriendId(account);
			revertfriend.setNickName("东邪");
			revertfriend.setNickNamePinYin("dong_xie");
			revertfriend.setStatus(1);
			revertfriend.setFriendOrigin(1);
			userFriendDao.saveAndFlush(revertfriend);
	   }
	}
	
	public static void main(String[] args){
		System.err.println(DigestUtils.md5Hex("1"));
	}
}
