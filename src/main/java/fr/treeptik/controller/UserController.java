package fr.treeptik.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.treeptik.exception.FormException;
import fr.treeptik.exception.ServiceException;
import fr.treeptik.maker.UserMaker;
import fr.treeptik.maker.UserToMaker;
import fr.treeptik.model.User;
import fr.treeptik.service.UserService;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/new.html", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView modelAndView = new ModelAndView("/admin/user/user");
		modelAndView.addObject("userMaker", new UserMaker());
		modelAndView.addObject("action", "Ajouter");
		return modelAndView;
	}

	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("id") Integer id) {
		try {
			ModelAndView modelAndView = new ModelAndView("/admin/user/user");
			User user = userService.findById(id);

			UserMaker userMaker = UserToMaker.To(user);

			modelAndView.addObject("userMaker", userMaker);
			modelAndView.addObject("action", "Editer");
			return modelAndView;
		} catch (Exception e) {
			return list();
		}
	}

	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("/admin/user/list-user");
		try {
			modelAndView.addObject("users", userService.findAll());
		} catch (Exception e) {
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;

	}

	@RequestMapping(value = "/save.html", method = RequestMethod.POST)
	public ModelAndView save(UserMaker userMaker) throws ServiceException {
		try {

			String login = userMaker.getLogin();
			String password = userMaker.getPassword();
			String passwordMatch = userMaker.getPasswordMatch();
			Boolean enabled = userMaker.getEnabled();
			String role = userMaker.getRole();
			
			if (login == null || login == "") {
				throw new FormException("Le login est obligatoires.");
			}
			if (password == null || password == "") {
				throw new FormException("Le mot de passe est obligatoires.");
			}
			if (enabled == null) {
				throw new FormException("Enabled est obligatoires.");
			}
			if (role == null) {
				throw new FormException("Role est obligatoires.");
			}
			String encryptPassword = "";
			if (password.equals(passwordMatch)) {
				Md5PasswordEncoder md5 = new Md5PasswordEncoder();
				encryptPassword = md5.encodePassword(userMaker.getPassword(), null); // TODO enlever constante
				
			} else {
				throw new FormException("Les mots de passe ne concordent pas.");
			}
			
			User user = null;
			if (userMaker.getId() == null) user = new User();
			else user = userService.findById(userMaker.getId());
			
			user.setId(userMaker.getId());
			user.setLogin(userMaker.getLogin());
			user.setPassword(encryptPassword);
			user.setEnabled(enabled);
			user.setRole(role);
			
			userService.save(user);

			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(userMaker.getId());
			modelAndView.addObject("error", e.getMessage());
			return modelAndView;
		}

	}
	
	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)
	public ModelAndView delete(@ModelAttribute("id") Integer id) {
		try {
			userService.remove(userService.findById(id));
			ModelAndView modelAndView = new ModelAndView("redirect:list.html");
			return modelAndView;
		} catch (Exception e) {
			ModelAndView modelAndView = edit(id);
			modelAndView.addObject("error", "Impossible de supprimer l'élément.");
			return modelAndView;
		}

	}
	

}
