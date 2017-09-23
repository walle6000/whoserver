package io.swagger.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.swagger.model.UserLableMap;

@Repository
public interface UserLableDao extends JpaRepository<UserLableMap, Integer>{

	public List<UserLableMap> findByLableType(Integer lableType);


}
