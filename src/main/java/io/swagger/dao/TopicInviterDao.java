package io.swagger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.model.TopicInviter;
import io.swagger.model.TopicTopology;
import io.swagger.model.User;

@Repository
public interface TopicInviterDao extends JpaRepository<TopicInviter, Long>{

	public List<TopicInviter> findBySender(String sender);
    
	public List<TopicInviter> findByReciver(String reciver);
	
	public List<TopicInviter> findByTopicId(Long topicId);
	
	@Query("from TopicInviter ti where ti.reciver=:userid and ti.status=1")
	public List<TopicInviter> findAllTopicInvite(@Param("userid") String userid);
	
	@Query("from TopicInviter ti where ti.sender=:userid and ti.status=0")
	public List<TopicInviter> findAllTopicRequest(@Param("userid") String userid);
	
	@Modifying
	@Query("update TopicInviter ti set ti.status=:status where ti.topicId=:topicId and ti.sender=:sender and ti.reciver=:reciver")
	public Integer updateInviteStatus(@Param("sender") String sender,@Param("reciver") String reciver,@Param("topicId") Long topicId,@Param("status") Integer status);
    
}
