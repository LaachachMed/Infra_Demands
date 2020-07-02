package com.orange.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.orange.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	@Query("Select u From User u where Login = :log and MDP_SESSION = :pass")
	public User connectUser(@Param("log")String login,@Param("pass")String pass);
	
	@Query("Select COUNT(*) From User u where login = :log and MDP_SESSION = :pass")
	public int countUser(@Param("log")String login,@Param("pass")String pass);
	
}
