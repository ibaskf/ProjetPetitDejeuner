package fr.treeptik.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.treeptik.model.Team;

public interface TeamDAO extends JpaRepository<Team, Integer>{
	
	Team findById(Integer id);
	Team findByname(Integer id);
	

}
