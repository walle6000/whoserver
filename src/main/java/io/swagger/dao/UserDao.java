package io.swagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

	public User findByUserid(String userid);

    @Query("from User u where u.userid=:userid")
    public User findUser(@Param("userid") String userid);
	
}
