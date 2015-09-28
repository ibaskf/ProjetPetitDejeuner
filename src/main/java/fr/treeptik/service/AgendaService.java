package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.AgendaDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Agenda;
@Service
public class AgendaService {

	private Logger logger = Logger.getLogger(AgendaService.class);

	@Autowired
	private AgendaDAO agendaDAO;

	@Transactional
	public Agenda save(Agenda agenda) throws Exception {
		logger.debug("appel de la methode save par " + agenda.getId());
		try {
			return agendaDAO.save(agenda);
		} catch (PersistenceException e) {
			logger.error("erreur save agenda " + e.getMessage());
			throw new ServiceException("erreur save agenda", e);
		}
	}

	public List<Agenda> findAll() throws ServiceException, DAOException {
		try {
			return agendaDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save personne", e);
		}
	}
	


		
	
	@Transactional
	public Agenda update(Agenda agenda) throws DAOException{
	
			return agendaDAO.save(agenda);
	
	}
	
	@Transactional
	public void remove(Agenda agenda) throws DAOException{
	  
		agendaDAO.delete(agenda);
			
	
	}
	
	
	@Transactional
	public void removeById(Integer id) throws DAOException{
	  
		agendaDAO.delete(id);
			
	
	}
	


	public Agenda findById(Integer id){
		return agendaDAO.findById(id);
	}
 
	public AgendaDAO getAgendaDAO() {
		return agendaDAO;
	}

	public void setAgendaDAO(AgendaDAO agendaDAO) {
		this.agendaDAO = agendaDAO;
	}

}
