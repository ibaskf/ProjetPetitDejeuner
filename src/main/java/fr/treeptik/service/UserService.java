package fr.treeptik.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.treeptik.dao.UserDao;
import fr.treeptik.model.User;
import fr.treeptik.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class UserService {

	private Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;

	@Transactional
	public User save(User user) throws ServiceException {
		logger.debug("appel de la methode save User " + user.getLogin());
		try {
			user.setLogin(user.getLogin().toLowerCase());
			return userDao.save(user);
		} catch (PersistenceException e) {
			logger.error("erreur save User " + e.getMessage());
			throw new ServiceException("erreur save User", e);
		}
	}

	@Transactional
	public void remove(User user) throws ServiceException {
		logger.debug("appel de la methode remove User " + user.getLogin());
		try {
			userDao.delete(user);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove User " + e.getMessage());
			throw new ServiceException("User id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove User " + e.getMessage());
			throw new ServiceException("erreur remove User", e);
		} catch (Exception e) {
			logger.error("erreur remove User " + e.getMessage());
			throw new ServiceException("erreur remove User", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById User id " + id);
		try {
			userDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove User " + e.getMessage());
			throw new ServiceException("User id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove User " + e.getMessage());
			throw new ServiceException("erreur removeById User", e);
		} catch (Exception e) {
			logger.error("erreur remove User " + e.getMessage());
			throw new ServiceException("erreur removeById User", e);
		}
	}
	
	public User findById(Integer id) throws ServiceException {
		try {
			return userDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById player", e);
		}
	}
	public List<User> findAll() throws ServiceException {
		try {
			return userDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll User", e);
		}
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}




}
