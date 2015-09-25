package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;

import fr.treeptik.model.Membre;

import fr.treeptik.service.MembreService;
import fr.treeptik.service.TeamService;

@Controller
@RequestMapping(value = "/membre")
public class MembreController {

	@Autowired
	private MembreService membreservice;

	@Autowired
	private TeamService teamservice;

	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException, DAOException {
		ModelAndView modelAndView = new ModelAndView("membre");
		
		modelAndView.addObject("membre", new Membre());
		modelAndView.addObject("teams", teamservice.findAll());
		return modelAndView;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("membre");
			Membre membre = membreservice.findById(id);
			modelAndView.addObject("membre", membre);
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list-membre");
		try {
			modelAndView.addObject("membres", membreservice.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(Membre membre) throws ServiceException {
		try {
			if (membre.getId() == null) {
				membreservice.save(membre);
			} else {
				membreservice.update(membre);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(membre.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		try {
			
				membreservice.removeById(id);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

	
}
