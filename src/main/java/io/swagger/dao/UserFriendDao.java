package io.swagger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.model.UserFriend;

@Repository
public interface UserFriendDao extends JpaRepository<UserFriend, Long>{

	@Modifying
	@Query("update UserFriend f set f.status = :status, f.createDate =:timeStamp  where f.id = :id")
	public void updateUserFriendStatusById(@Param("id") Long id,@Param("status") Integer status, @Param("timeStamp") Long timeStamp);
	
	@Modifying
	@Query("update UserFriend f set f.shareFriend = :shareFriend where f.id = :id")
	public void updateUserFriendShareById(@Param("id") Long id,@Param("shareFriend") Integer shareFriend);
	
	@Modifying
	@Query("update UserFriend f set f.shareFriend = :shareFriend where f.id in (:id_group)")
	public void updateUserFriendShareByIdGroup(@Param("id_group") List<Long> id_group,@Param("shareFriend") Integer shareFriend);
	
	@Modifying
	@Query("update UserFriend f set f.shareFriend = :shareFriend where f.userId in (:friendGroup) and f.friendId = :userId")
	public void requestUserFriendShareByIdGroup(@Param("friendGroup") List<String> friendGroup,@Param("userId")String userId,@Param("shareFriend") Integer shareFriend);
	
	@Modifying
	@Query("update UserFriend f set f.shareFriend = 0 where f.friendId = :userId")
	public void clearRequestUserFriendShareByUserId(@Param("userId")String userId);

	
	@Query("from UserFriend f where f.status = 1 and f.userId = :userId")
	public List<UserFriend> findAllConfirmedFriendsOfUser(@Param("userId") String userId);
	
	//I am the friend of my friends and my friend sharing statuses are stored in my friends's record items.
	@Query("from UserFriend f where f.status = 1 and f.shareFriend != 0 and f.friendId = :userId")
	public List<UserFriend> findRequestedFriendsofUser(@Param("userId") String userId);	
	
	//shareFriend = 2, agreed to share informations
	@Query("from UserFriend f where f.status = 1 and f.shareFriend = 2 and f.userId = :userId")
	public List<UserFriend> findAllInfoSharingFriends(@Param("userId") String userId);	
	
	@Query("from UserFriend f where f.status = 1 and f.userId = :userId and (f.shareFriend = 2 or f.shareFriend = 1)")
	public List<UserFriend> findAllSharingFriendsByUserId(@Param("userId") String userId);	
	
	@Query("select count(f) from UserFriend f where f.status = 1 and f.shareFriend = 2 and f.userId = :userId")
	public Long getAgreedFriendsNum(@Param("userId") String userId);	
	
	@Modifying
	@Query("update UserFriend f set f.nickName = :nickName,f.nickNamePinYin = :nickNamePinYin, f.labelRelationship = :labelRelationship, f.labelImpression = :labelImpression, f.isFavorite = :isFavorite, f.friendOrigin = :friendOrigin, f.description = :description where f.id = :id")
	public void updateUserFriendInfo(@Param("id") Long id,@Param("nickName") String nickName,@Param("nickNamePinYin") String nickNamePinYin,@Param("labelRelationship") String labelRelationship,@Param("labelImpression") String labelImpression,@Param("isFavorite") Integer isFavorite,@Param("friendOrigin") Integer friendOrigin,@Param("description") String description);
	
	@Query("from UserFriend f where f.status = 1 and f.userId = :userId and f.isFavorite > 0")
	public List<UserFriend> getFavoriteFriends(@Param("userId") String userId);
}
