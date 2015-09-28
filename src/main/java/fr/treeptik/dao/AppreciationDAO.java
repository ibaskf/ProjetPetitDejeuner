package fr.treeptik.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.treeptik.model.Appreciation;


@Repository
public interface AppreciationDAO extends JpaRepository<Appreciation,Integer>{


	Appreciation findById(Integer id);
	
	
	
	

}