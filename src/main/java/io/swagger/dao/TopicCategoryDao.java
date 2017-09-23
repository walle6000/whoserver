package io.swagger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.model.Topic;
import io.swagger.model.TopicCategory;

@Repository
public interface TopicCategoryDao extends JpaRepository<TopicCategory, Long>{

	@Query("from TopicCategory t where (t.userId = :userId or t.userId = 'commonUser') and t.status = 0")
	public List<TopicCategory> findUserTopicCategory(@Param("userId") String userId);
	
	@Query("from TopicCategory t where t.userId = 'commonUser' and t.status = 0")
	public List<TopicCategory> findCommonTopicCategory();
    
	@Modifying
    @Query("update TopicCategory t set t.status=:status where t.userId=:userid and t.categoryType =:categoryType")
    public void updateTopicCategory(@Param("userid") String userid,@Param("categoryType")Integer categoryType,@Param("status")Integer status);	
	
}
