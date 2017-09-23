package io.swagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import io.swagger.model.User;
import io.swagger.model.InfoExplorable;

@Repository
public interface InfoExplorableDao extends JpaRepository<InfoExplorable, Long>{

	@Query("from InfoExplorable ie where ie.userId=?1 and ie.status = 0")
	public InfoExplorable findByUserId(String userId);
	
    @Modifying
    @Query("update InfoExplorable ie set ie.userId=:userId,ie.infoTitle=:infoTitle,ie.infoContent=:infoContent,ie.infoAddition=:infoAddition,ie.createdTime=:createdTime,ie.postImages=:postImages where ie.id=:id")
    public void updateInfoExplorable(
    		@Param("id") Long id,
    		@Param("userId") String userId,
    		@Param("infoTitle") String infoTitle,
    		@Param("infoContent") String infoContent,
    		@Param("infoAddition") String infoAddition,
    		/*@Param("views") Long views,
    		@Param("thumbsUp") Long thumbsUp,*/
    		@Param("createdTime") Long createdTime,
    		@Param("postImages") String postImages);
    
    @Modifying
    @Query("update InfoExplorable ie set ie.status = 1 where ie.userId=?1")
    public Integer DeleteByUserId(String userId);
}

