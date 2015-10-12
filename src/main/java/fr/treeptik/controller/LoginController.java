package fr.treeptik.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@RequestMapping("admin")
public class LoginController {
  
  @RequestMapping(value="/index.html", method = RequestMethod.GET)
  public  ModelAndView printWelcomeadmin( ) {
 
	  ModelAndView modelAndView = new ModelAndView("admin/index");
	  return modelAndView;
  }
  
}