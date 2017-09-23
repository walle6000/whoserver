package io.swagger.common;

public enum CacheType {
	//User Info
	verfifyCode,
	userInfo,
	whoId,
	userLaud,
	
	//Friend Info
	friendRequestList,
	mySentfriendRequestList,
	friendList,
	myFavoriteList,
	friendLableList,
	shareFriendList,
	
	//Token Info
	tokenKey,
	topicMessage,
	
	//User Log
	UserSearchLog,
	
	//explorable information
	infoExplore,
	infoThumbsup,
	infoViews,
	
	//comments
	commentThumbsup,
	commentViews,
	infoCommentNum,

	//Topic
	topicInfo,
	createdTopicList,
	joinedUser,
	joinedTopicTopology,
	invitedTopicTopology,
	invitedTopicList,
	invitingTopicList,
	topicCategory,
	topicAttitudeList
}
