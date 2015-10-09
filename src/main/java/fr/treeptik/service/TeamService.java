package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import fr.treeptik.dao.TeamDAO;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Membre;
import fr.treeptik.model.Team;
@Service
public class TeamService {

	private Logger logger = Logger.getLogger(TeamService.class);

	@Autowired
	private TeamDAO teamDAO;

	@Transactional
	public Team save(Team team) throws Exception {
		logger.debug("appel de la methode save par " + team.getName());
		try {
			return teamDAO.save(team);
		} catch (PersistenceException e) {
			logger.error("erreur save team " + e.getMessage());
			throw new ServiceException("erreur save team", e);
		}
	}

	public List<Team> findAll() throws ServiceException, DAOException {
		try {
			return teamDAO.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur save team", e);
		}
	}
	


		
	
	@Transactional
	public Team update(Team team) throws DAOException{
	
			return teamDAO.save(team);
	
	}
	
	@Transactional
	public void remove(Team team) throws DAOException{
	  
		teamDAO.delete(team);
			
	
	}
	
	
	@Transactional
	public void removeById(Integer id) throws DAOException{
	  
		teamDAO.delete(id);
			
	
	}
	

	public Team findById(Integer id){
		return teamDAO.findById(id);
	}
	
	
	public List<Membre> findMembreByTeam(Integer id){
		return teamDAO.findMembreByTeam(id);
	}

	

	public TeamDAO getTeamDAO() {
		return teamDAO;
	}

	public void setTeamDAO(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}

}
