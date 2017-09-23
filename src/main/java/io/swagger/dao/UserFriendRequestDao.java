package io.swagger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.model.User;
import io.swagger.model.UserFriendRequest;

@Repository
public interface UserFriendRequestDao extends JpaRepository<UserFriendRequest, Long>{

	@Query("from UserFriendRequest r where r.decisionId = :userid and r.status != 2")
    public List<UserFriendRequest> findAllUserRequest(@Param("userid") String userid);
	
	@Query("from UserFriendRequest r where r.requesterId = :userid and r.status != 2")
    public List<UserFriendRequest> findAllSentRequest(@Param("userid") String userid);
	
	@Modifying
	@Query("update UserFriendRequest r set r.status = :status where r.id = :requestid")
	public void updateUserRequestStatusById(@Param("requestid") Long requestid,@Param("status") Integer status);
	
}
