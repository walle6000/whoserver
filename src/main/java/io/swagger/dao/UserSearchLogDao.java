package io.swagger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.model.User;
import io.swagger.model.UserSearchLog;

@Repository
public interface UserSearchLogDao extends JpaRepository<UserSearchLog, Long>{

	public List<UserSearchLog> findByOwnId(String ownId);
	
}
