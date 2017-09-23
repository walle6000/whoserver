package io.swagger.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.model.TopicAttitude;

@Repository
public interface TopicAttitudeDao extends JpaRepository<TopicAttitude, Long>{

	
}
