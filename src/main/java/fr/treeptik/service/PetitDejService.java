package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.PetitDejDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Membre;
import fr.treeptik.model.PetitDej;
@Service
public class PetitDejService {

	private Logger logger = Logger.getLogger(PetitDejService.class);

	@Autowired
	private PetitDejDAO petitDejDAO;

	@Transactional
	public PetitDej save(PetitDej petitDej) throws Exception {
		logger.debug("appel de la methode save par " + petitDej.getId());
		try {
			return petitDejDAO.save(petitDej);
		} catch (PersistenceException e) {
			logger.error("erreur save petitDej " + e.getMessage());
			throw new ServiceException("erreur save petitDej", e);
		}
	}

	public List<PetitDej> findAll() throws ServiceException, DAOException {
		try {
			return petitDejDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save personne", e);
		}
	}
	
	public List<Membre> find(Integer id) throws ServiceException, DAOException {
		try {
			return petitDejDAO.find(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save personne", e);
		}
	}
	


		
	
	@Transactional
	public PetitDej update(PetitDej petitDej) throws DAOException{
	
			return petitDejDAO.save(petitDej);
	
	}
	
	@Transactional
	public void remove(PetitDej petitDej) throws DAOException{
	  
		petitDejDAO.delete(petitDej);
			
	
	}
	
	
	
	@Transactional
	public void removeById(Integer id) throws DAOException{
	  
		petitDejDAO.delete(id);
			
	
	}
	
	public List<PetitDej> findByName(String name){
		return petitDejDAO.findByName(name);
		
	}

	public PetitDej findById(Integer id){
		return petitDejDAO.findById(id);
	}
 
	public PetitDejDAO getPetitDejDAO() {
		return petitDejDAO;
	}

	public void setPetitDejDAO(PetitDejDAO petitDejDAO) {
		this.petitDejDAO = petitDejDAO;
	}

}
