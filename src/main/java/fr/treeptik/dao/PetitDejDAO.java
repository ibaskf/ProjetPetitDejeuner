package fr.treeptik.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.treeptik.model.PetitDej;


@Repository
public interface PetitDejDAO extends JpaRepository<PetitDej,Integer>{

	List<PetitDej> findByName(String name);
	PetitDej findById(Integer id);
	
	
	
	

}