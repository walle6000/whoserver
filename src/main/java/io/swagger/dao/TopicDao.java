package io.swagger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.model.Topic;

@Repository
public interface TopicDao extends JpaRepository<Topic, Long>{

	@Query("from Topic t where t.status = 0 and t.createUser = :userId")
	public List<Topic> findAllPendingTopics(@Param("userId") String userId);
	
	@Query("from Topic t where t.createUser = :userId")
	public List<Topic> findAllCreatedTopics(@Param("userId") String userId);
    
}
