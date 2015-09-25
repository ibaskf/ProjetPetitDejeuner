package fr.treeptik.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.treeptik.model.Agenda;


@Repository
public interface AgendaDAO extends JpaRepository<Agenda,Integer>{

	List<Agenda> findByName(String name);
	Agenda findById(Integer id);
	
	
	
	

}