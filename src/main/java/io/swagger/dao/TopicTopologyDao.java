package io.swagger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.model.TopicTopology;
import io.swagger.model.User;

@Repository
public interface TopicTopologyDao extends JpaRepository<TopicTopology, Long>{

	public List<TopicTopology> findBySender(String sender);
    
	public List<TopicTopology> findByReciver(String reciver);
	
	public List<TopicTopology> findByTopicId(Long topicId);
	
	@Modifying
    @Query("update TopicTopology tt set tt.status=:status,tt.comment=:comment,tt.selection=0 where tt.id=:topologyId")
    public void updateTopologyById(@Param("topologyId") Long topologyId,@Param("status")String status,@Param("comment")String comment);
	
	@Modifying
    @Query("update TopicTopology tt set tt.selection=:selection where tt.id=:topologyId")
    public void updateSelectionById(@Param("topologyId") Long topologyId,@Param("selection")Integer selection);
    
}
