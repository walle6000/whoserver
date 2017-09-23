package io.swagger.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.model.UserProfile;

@Repository
public interface UserProfileDao extends JpaRepository<UserProfile, Long>{

    @Modifying
    @Query("update UserProfile up set up.userName=:userName,up.userPinYin=:userPinYin,up.gender=:gender,up.email=:email,up.phone=:phone,up.weixin=:weixin,up.qq=:qq,up.headIcon=:headicon,up.thumbnailheadIcon=:thumbnailheadIcon,up.summry=:summry,up.birthDay=:birthDay,up.homeTown=:homeTown,up.tag=:tag,up.others=:others where up.id=:id")
    public void updateUserProfile(
    		@Param("id") Long id,
    		@Param("userName") String userName,
    		@Param("userPinYin") String userPinYin,
    		@Param("gender") String gender,
    		@Param("email") String email,
    		@Param("phone") String phone,
    		@Param("weixin") String weixin,
    		@Param("qq") String qq,
    		@Param("headicon") String headicon,
    		@Param("thumbnailheadIcon") String thumbnailheadIcon,
    		@Param("summry") String summry,
    		@Param("birthDay") String birthDay,
    		@Param("homeTown") String homeTown,
    		@Param("tag") String tag,
    		@Param("others") String others);	
    
    @Modifying
    @Query("update UserProfile up set up.lastLogin=:lastLogin where up.id=:id")
    public void updateUserLastLogin(
    		@Param("id") Long id,
    		@Param("lastLogin") Long lastLogin);	
}
