package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;

import fr.treeptik.model.PetitDej;

import fr.treeptik.service.PetitDejService;
import fr.treeptik.service.TeamService;

@Controller
@RequestMapping(value = "/petitDej")
public class PetitDejController {

	@Autowired
	private PetitDejService petitDejservice;

	@Autowired
	private TeamService teamservice;

	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException, DAOException {
		ModelAndView modelAndView = new ModelAndView("petitDej");
		
		modelAndView.addObject("petitDej", new PetitDej());
		modelAndView.addObject("teams", teamservice.findAll());
		return modelAndView;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("petitDej");
			PetitDej petitDej = petitDejservice.findById(id);
			modelAndView.addObject("petitDej", petitDej);
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list-petitDej");
		try {
			modelAndView.addObject("petitDejs", petitDejservice.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(PetitDej petitDej) throws ServiceException {
		try {
			if (petitDej.getId() == null) {
				petitDejservice.save(petitDej);
			} else {
				petitDejservice.update(petitDej);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(petitDej.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		try {
			
				petitDejservice.removeById(id);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

	
}
