package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.MembreDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Membre;
@Service
public class MembreService {

	private Logger logger = Logger.getLogger(MembreService.class);

	@Autowired
	private MembreDAO membreDAO;

	@Transactional
	public Membre save(Membre membre) throws Exception {
		logger.debug("appel de la methode save par " + membre.getName()+ membre.getPreference());
		try {
			return membreDAO.save(membre);
		} catch (PersistenceException e) {
			logger.error("erreur save membre " + e.getMessage());
			throw new ServiceException("erreur save membre", e);
		}
	}

	public List<Membre> findAll() throws ServiceException, DAOException {
		try {
			return membreDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save personne", e);
		}
	}
	



		
	
	@Transactional
	public Membre update(Membre membre) throws DAOException{
	
			return membreDAO.save(membre);
	
	}
	
	@Transactional
	public void remove(Membre membre) throws DAOException{
	  
		membreDAO.delete(membre);
			
	
	}
	
	
	@Transactional
	public void removeById(Integer id) throws DAOException{
	  
		membreDAO.delete(id);
			
	
	}
	
	public List<Membre> findByName(String name){
		return membreDAO.findByName(name);
		
	}
	
	public Membre findByLogin(String name){
		return membreDAO.findByLogin(name);
		
	}

	public Membre findById(Integer id){
		return membreDAO.findById(id);
	}
 
	public MembreDAO getMembreDAO() {
		return membreDAO;
	}

	public void setMembreDAO(MembreDAO membreDAO) {
		this.membreDAO = membreDAO;
	}

}
