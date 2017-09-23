package io.swagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

	public User findByUserid(String userid);

    @Query("from User u where u.userid=:userid")
    public User findUser(@Param("userid") String userid);
    
    @Modifying
    @Query("update User u set u.password=:newPwd where u.userid=:userid")
    public void updateUserPassword(@Param("userid") String userid,@Param("newPwd")String newPwd);	
    
    @Query("from User u where u.userid=:phoneId or u.id=:phoneId")
    public User findOneByPhoneOrId(@Param("phoneId") String phoneId);
}
