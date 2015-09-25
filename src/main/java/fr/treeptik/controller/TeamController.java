package fr.treeptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.exception.ServiceException;


import fr.treeptik.model.Team;

import fr.treeptik.service.TeamService;

@Controller
@RequestMapping(value = "/team")
public class TeamController {

	@Autowired
	private TeamService teamservice;

	@RequestMapping(value = "/new.do", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("team");
		modelAndView.addObject("team", new Team());
		return modelAndView;
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("team");
			Team team = teamservice.findById(id);
			modelAndView.addObject("team", team);
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("list-team");
		try {
			modelAndView.addObject("teams", teamservice.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public ModelAndView save(Team team) throws ServiceException {
		try {
			if (team.getId() == null) {
				teamservice.save(team);
			} else {
				teamservice.update(team);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(team.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		try {
			
				teamservice.removeById(id);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.do");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

	
}

