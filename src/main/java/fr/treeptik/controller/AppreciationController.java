package fr.treeptik.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Appreciation;
import fr.treeptik.model.Membre;
import fr.treeptik.model.PetitDej;
import fr.treeptik.service.AppreciationService;
import fr.treeptik.service.MembreService;
import fr.treeptik.service.PetitDejService;

@Controller
@RequestMapping(value = {"utilisateur/appreciation","admin/apprecition"})
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
	public ModelAndView add(@RequestParam HttpServletRequest request) {

		ModelAndView modelAndView = null;
		if ((request.getRequestURL().toString()).contentEquals("admin")){
		 modelAndView = new ModelAndView("admin/apreciation/apreciation");
		}
		else if ((request.getRequestURL().toString()).contentEquals("utilisateur")){
			 modelAndView = new ModelAndView("utilisateur/apreciation/apreciation");
			}
		
		modelAndView.addObject("apprecition", new Appreciation());
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id,HttpServletRequest request) throws ServiceException, DAOException {
		 Authentication authentication = SecurityContextHolder.getContext().
	             getAuthentication();
	String membrelogin=authentication.getName();
		String datejour= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		ModelAndView modelAndView =null;
		if ((request.getRequestURL().toString()).contains("admin")){
			 modelAndView = new ModelAndView("admin/petitdej/detail-petitdej");
			}
			else if ((request.getRequestURL().toString()).contains("utilisateur")){
				 modelAndView = new ModelAndView("utilisateur/petitdej/detail-petitdej");
				}
		
		
		modelAndView.addObject("petitDej", petitdejservice.findById(id));
		modelAndView.addObject("membres", petitdejservice.find(id));
		modelAndView.addObject("login", membrelogin);
		modelAndView.addObject("mbloger", membreservice.findByLogin(membrelogin));
		modelAndView.addObject("datejour", datejour);
		
		modelAndView.addObject("appreciation", new Appreciation());
		modelAndView.addObject("appreciations", appreciationservice.findByPetitDej(petitdejservice.findById(id)));
		

		return modelAndView;

	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("utilisateur/appreciation/list-appreciation");
		try {
			modelAndView.addObject("appreciations", appreciationservice.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(Appreciation appreciation,HttpServletRequest request) throws ServiceException, DAOException {
		try {
			if (appreciation.getId() == null) {
				appreciationservice.save(appreciation);
			} else {
				appreciationservice.update(appreciation);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:../petitdej/list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(appreciation.getId(),request);
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

