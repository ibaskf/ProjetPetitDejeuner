package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.treeptik.dao.AppreciationDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Appreciation;
import fr.treeptik.model.PetitDej;
@Service
public class AppreciationService {

	private Logger logger = Logger.getLogger(AppreciationService.class);

	@Autowired
	private AppreciationDAO appreciationDAO;

	@Transactional
	public Appreciation save(Appreciation appreciation) throws Exception {
		logger.debug("appel de la methode save par " + appreciation.getId());
		try {
			return appreciationDAO.save(appreciation);
		} catch (PersistenceException e) {
			logger.error("erreur save appreciation " + e.getMessage());
			throw new ServiceException("erreur save appreciation", e);
		}
	}

	public List<Appreciation> findAll() throws ServiceException, DAOException {
		try {
			return appreciationDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save personne", e);
		}
	}
	


		
	
	@Transactional
	public Appreciation update(Appreciation appreciation) throws DAOException{
	
			return appreciationDAO.save(appreciation);
	
	}
	
	@Transactional
	public void remove(Appreciation appreciation) throws DAOException{
	  
		appreciationDAO.delete(appreciation);
			
	
	}
	
	
	@Transactional
	public void removeById(Integer id) throws DAOException{
	  
		appreciationDAO.delete(id);
			
	
	}


	public Appreciation findById(Integer id){
		return appreciationDAO.findById(id);
	}
	
	public List<Appreciation> findByPetitDej(PetitDej petitd){
		return appreciationDAO.findByPetitdej(petitd);
	}
 
	public AppreciationDAO getAppreciationDAO() {
		return appreciationDAO;
	}

	public void setAppreciationDAO(AppreciationDAO appreciationDAO) {
		this.appreciationDAO = appreciationDAO;
	}

}
