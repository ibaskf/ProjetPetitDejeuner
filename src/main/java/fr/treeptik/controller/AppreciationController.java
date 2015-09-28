package fr.treeptik.controller;

import java.beans.PropertyEditorSupport;
import java.util.List;

import org.hibernate.type.CustomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Appreciation;
import fr.treeptik.model.Membre;
import fr.treeptik.model.PetitDej;
import fr.treeptik.model.Team;
import fr.treeptik.service.AppreciationService;
import fr.treeptik.service.MembreService;
import fr.treeptik.service.PetitDejService;
import fr.treeptik.service.TeamService;

@Controller
@RequestMapping(value = "admin/appreciation")
public class AppreciationController {

	@Autowired
	private AppreciationService appreciationservice;
	@Autowired
	private MembreService membreservice;
	@Autowired
	private PetitDejService petitdejservice;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
	    binder.registerCustomEditor(Membre.class, "membre", new PropertyEditorSupport() {
	    @Override
	    public void setAsText(String text) {
	    	Membre ch = membreservice.findById(Integer.parseInt(text));
	        setValue(ch);
	    }
	    });
	    
	    binder.registerCustomEditor(PetitDej.class, "petitdej", new PropertyEditorSupport() {
		    @Override
		    public void setAsText(String text) {
		    	PetitDej ch = petitdejservice.findById(Integer.parseInt(text));
		        setValue(ch);
		    }
		    });
	}
	
	
	
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("apreciation/apreciation");
		modelAndView.addObject("apprecition", new Appreciation());
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("appreciation/appreciation");
			Appreciation appreciation = appreciationservice.findById(id);
			modelAndView.addObject("appreciation", appreciation);
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("appreciation/list-appreciation");
		try {
			modelAndView.addObject("appreciations", appreciationservice.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(Appreciation appreciation) throws ServiceException {
		try {
			if (appreciation.getId() == null) {
				appreciationservice.save(appreciation);
			} else {
				appreciationservice.update(appreciation);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:../petitdej/list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(appreciation.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		try {
			
			appreciationservice.removeById(id);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}

	
}

