package fr.treeptik.controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.editor.TypeDejEditor;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;

import fr.treeptik.model.Membre;
import fr.treeptik.model.TypeDej;
import fr.treeptik.service.MembreService;
import fr.treeptik.service.TeamService;

@Controller
@RequestMapping(value = "/admin/membre")
public class MembreController {

	@Autowired
	private MembreService membreservice;

	@Autowired
	private TeamService teamservice;
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	 
	binder.registerCustomEditor(TypeDej.class,new TypeDejEditor(TypeDej.class));
	
	}
	
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() throws ServiceException, DAOException {
		ModelAndView modelAndView = new ModelAndView("membre/membre");
		
		modelAndView.addObject("membre", new Membre());
		modelAndView.addObject("teams", teamservice.findAll());
		modelAndView.addObject("typedej",TypeDej.values());
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("membre/membre");
			Membre membre = membreservice.findById(id);
			modelAndView.addObject("membre", membre);
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("membre/list-membre");
		try {
			modelAndView.addObject("membres", membreservice.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid Membre membre,BindingResult result) throws ServiceException {
		 if(result.hasErrors()){
			 System.out.println(result.getAllErrors().toString());
			 
			 }
		
		try {
			if (membre.getId() == null) {
				membreservice.save(membre);
			} else {
				membreservice.update(membre);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(membre.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		try {
			
				membreservice.removeById(id);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

	
}
