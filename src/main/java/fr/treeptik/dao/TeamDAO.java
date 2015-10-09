package fr.treeptik.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.treeptik.model.Membre;
import fr.treeptik.model.Team;

public interface TeamDAO extends JpaRepository<Team, Integer>{
	
	Team findById(Integer id);
	Team findByname(Integer id);
	
	@Query("SELECT t.membres from Team t where t.id = :id")
	List<Membre> findMembreByTeam(@Param("id") Integer id);
	

}
