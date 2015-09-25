package fr.treeptik.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.treeptik.model.Membre;


@Repository
public interface MembreDAO extends JpaRepository<Membre,Integer>{

	List<Membre> findByName(String name);
	Membre findById(Integer id);
	
	
	
	

}
