package io.swagger.dao;

import java.util.Date;
//import java.util.Objects;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;






import io.swagger.model.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Long>{

	@Modifying
	@Query("from Comment c where c.infoId = :infoId and c.status !=0 order by c.createdTime desc")
	public List<Comment> findByInfoId(@Param("infoId") Long infoId);
	
	@Query("select count(c) from Comment c where c.infoId = :infoId and c.status !=0")
	public Long findCommentNumByInfoId(@Param("infoId") Long infoId);
	
	@Query("delete from Comment c where c.infoId = :infoId")
	public void deleteRelatedComments(@Param("infoId") Long infoId);
	
	@Query("update Comment c set c.status = :status where c.infoId = :infoId")
	public void updateStatusbyInfoId(@Param("infoId") Long infoId, @Param("status") Integer status);

}

