package fr.treeptik.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.editor.TypeDejEditor;
import fr.treeptik.exception.DAOException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Appreciation;
import fr.treeptik.model.Membre;
import fr.treeptik.model.PetitDej;
import fr.treeptik.model.Team;
import fr.treeptik.model.TypeDej;
import fr.treeptik.service.AppreciationService;
import fr.treeptik.service.MembreService;
import fr.treeptik.service.PetitDejService;
import fr.treeptik.service.TeamService;

@Controller
@RequestMapping(value = {"utilisateur/petitdej","admin/petitdej"})
public class PetitDejController {

	@Autowired
	private PetitDejService petitDejservice;

	@Autowired
	private MembreService membreservice;
	
	@Autowired
	private AppreciationService appreciationservice;
	
	@Autowired
	private TeamService teamService;
	
	
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "membres", new CustomCollectionEditor(List.class) {
        	
        	
        	Integer id=null;
        	@Override
        	protected Object convertElement(Object element) {
        		if(element instanceof Membre) {
        		
        			return element;
        		}
        		if(element instanceof Integer) {
        		
        			Membre membre = new Membre();
        			membre.setId(Integer.valueOf((String) element));
        				id=(Integer) element;
        				return id != null ? membreservice.findById(id) : null;
        			
        		}
        		if(element instanceof String) {
        			
        			Membre membre = new Membre();
        				membre.setId(Integer.parseInt( (String) element));
        			return membre;
        			
        		}
        		return super.convertElement(element);
        	}
        });
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy");
        
        binder.registerCustomEditor(Date.class, "date", new CustomDateEditor(sdf2, false));
 
        binder.registerCustomEditor(Membre.class, "organisateur", new PropertyEditorSupport() {
		    @Override
		    public void setAsText(String text) {
		    	Membre ch = membreservice.findById(Integer.parseInt(text));
		        setValue(ch);
		    }
		    });
    	binder.registerCustomEditor(TypeDej.class,new TypeDejEditor(TypeDej.class));
    	
    	
    	  binder.registerCustomEditor(Team.class, "team", new PropertyEditorSupport() {
    		    @Override
    		    public void setAsText(String text) {
    		    	Team ch = teamService.findById(Integer.parseInt(text));
    		        setValue(ch);
    		    }
    		    });
	}
	

	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add( HttpServletRequest request) throws ServiceException, DAOException {
	
		 Authentication authentication = SecurityContextHolder.getContext().
	             getAuthentication();
	String membrelogin=authentication.getName();
	ModelAndView modelAndView = null;
	if ((request.getRequestURL().toString()).contains("admin")){
	 modelAndView = new ModelAndView("admin/petitdej/petitdej");
	}
	else if ((request.getRequestURL().toString()).contains("utilisateur")){
		 modelAndView = new ModelAndView("utilisateur/petitdej/petitdej");
		}
	
		modelAndView.addObject("petitDej", new PetitDej());
		modelAndView.addObject("membres", membreservice.findAll());
		modelAndView.addObject("teams", teamService.findAll());
		modelAndView.addObject("membreloger",membreservice.findByLogin(membrelogin));
		modelAndView.addObject("typedej",TypeDej.values());
		return modelAndView;
	}
	
	@RequestMapping(value = "/detail.html", method = RequestMethod.GET)
	public ModelAndView detail(@ModelAttribute("id") Integer id,HttpServletRequest request) throws ServiceException, DAOException {

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
		
		
		modelAndView.addObject("petitDej", petitDejservice.findById(id));
		modelAndView.addObject("membres", petitDejservice.find(id));
		modelAndView.addObject("login", membrelogin);
		modelAndView.addObject("mbloger", membreservice.findByLogin(membrelogin));
		modelAndView.addObject("datejour", datejour);
		
		modelAndView.addObject("appreciation", new Appreciation());
		modelAndView.addObject("appreciations", appreciationservice.findByPetitDej(petitDejservice.findById(id)));
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id,HttpServletRequest request) {
		try {
			ModelAndView modelAndView=null;
			if ((request.getRequestURL().toString()).contains("admin")){
				 modelAndView = new ModelAndView("admin/petitdej/petitdej");
				}
				else if ((request.getRequestURL().toString()).contains("utilisateur")){
					 modelAndView = new ModelAndView("utilisateur/petitdej/petitdej");
					}
			
			 Authentication authentication = SecurityContextHolder.getContext().
		             getAuthentication();
		String membrelogin=authentication.getName();
			PetitDej petitDej = petitDejservice.findById(id);
			modelAndView.addObject("petitDej", petitDej);
			modelAndView.addObject("membres", membreservice.findAll());
			modelAndView.addObject("teams", teamService.findAll());
			modelAndView.addObject("membreloger",membreservice.findByLogin(membrelogin));
			modelAndView.addObject("typedej",TypeDej.values());
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			return list(request);
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		 Authentication authentication = SecurityContextHolder.getContext().
	             getAuthentication();
	String membrelogin=authentication.getName();
	
	ModelAndView modelAndView=null;
	if ((request.getRequestURL().toString()).contains("admin")){
		 modelAndView = new ModelAndView("admin/petitdej/petitdej-list");
		}
		else if ((request.getRequestURL().toString()).contains("utilisateur")){
			 modelAndView = new ModelAndView("utilisateur/petitdej/petitdej-list");
			}
	
		try {
			
			modelAndView.addObject("petitDejs", petitDejservice.findAll());
			modelAndView.addObject("login", membreservice.findByLogin(membrelogin));
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute @Valid PetitDej petitDej,HttpServletRequest request,BindingResult result) throws ServiceException {
		
		System.out.println(petitDej.toString());
		
		if(result.hasErrors()){
			 System.out.println(result.getAllErrors().toString());
			 
			 }
		try {
			
			if (petitDej.getId() == null) {
				petitDejservice.save(petitDej);
			} else {
				petitDejservice.update(petitDej);
			}
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(petitDej.getId(), request);
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) throws ServiceException {
		try {
			
				petitDejservice.removeById(id);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}
	

	@RequestMapping(value = "/deletepart.html", method = RequestMethod.GET)
	public ModelAndView deletepart(@ModelAttribute("id") Integer id,@ModelAttribute("idm") Integer idm) throws ServiceException {
		try {
			
				petitDejservice.removeparticipant(id,idm);
			
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
		
			ModelAndView modelAndView = null;
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}
	}


	@RequestMapping(value = "/listparticipant.html", method = RequestMethod.GET)
	public ModelAndView listparticipant(@ModelAttribute("id") Integer id,HttpServletRequest request) {
		ModelAndView modelAndView=null;
		if ((request.getRequestURL().toString()).contains("admin")){
			 modelAndView = new ModelAndView("admin/petitdej/list-participant");
			}
			else if ((request.getRequestURL().toString()).contains("utilisateur")){
				 modelAndView = new ModelAndView("utilisateur/petitdej/list-participant");
				}
	

			try {
				
				if(!petitDejservice.find(id).isEmpty()){
				modelAndView.addObject("membres", petitDejservice.find(id));
				}else{
					modelAndView.addObject("membres",teamService.findMembreByTeam(petitDejservice.findById(id).getTeam().getId()));
				}
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			return modelAndView;

		
	}
}
