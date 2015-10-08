package fr.treeptik.conf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.treeptik.exception.ServiceException;
import fr.treeptik.model.Membre;
import fr.treeptik.model.PetitDej;
import fr.treeptik.model.User;
import fr.treeptik.service.MembreService;
import fr.treeptik.service.PetitDejService;
import fr.treeptik.service.UserService;

@Component
public class InitialisationBase {
	public InitialisationBase() {

	}

	// ============================ SERVICE =================================
	@Autowired
	private PetitDejService petitdejService;
	@Autowired
	private MembreService membreService;
	
	@Autowired
	private UserService userservice;
	
	
	public void run(){
	initMembre();
	initPetitdej();
	inituser();
	}

	// attention à l'ordre d'init
	private Membre m1;
	private Membre m2;
	private Membre m3;
	
	private void initMembre() {
		m1=new Membre();
		
		m1.setFirstname("test");
		m1.setLogin("admin");
		
		m1.setName("name");
		
		m2=new Membre();
	
		m2.setFirstname("testf");
		m2.setLogin("m2");
	
		m2.setName("name1");
		
		
		m3=new Membre();
	
		m3.setFirstname("testf1");
		m3.setLogin("m3");
		
		m3.setName("name2");
		
		
		try {
			membreService.save(m1);
			membreService.save(m2);
			

			membreService.save(m3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
	}
	
	
	private PetitDej petitdej ;
	private PetitDej petitdej1 ;
	private PetitDej petitdej2 ;
	
	private void initPetitdej() {
		List<Membre> mlist=new ArrayList<Membre>();
		List<Membre> mlist1=new ArrayList<Membre>();
		mlist.add(m1);
		mlist.add(m2);
		mlist1.add(m2);
		mlist1.add(m3);
		
		petitdej=new PetitDej();
		petitdej1=new PetitDej();
		petitdej2=new PetitDej();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		String dateInString = "30-09-2015";
		Date date;
		try {
			date = sdf.parse(dateInString);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-M-yyyy");
	
		Date date1 = sdf.parse("12-10-2015");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-M-yyyy");
	
		Date date2 = sdf.parse("28-09-2015");
		
		petitdej.setDate(date);
		petitdej.setName("petitdej1");
		petitdej.setMembres(mlist);
		petitdej.setOrganisateur(m1);
		
		petitdej1.setDate(date1);
		petitdej1.setName("petitdej2");
		petitdej1.setMembres(mlist1);
		petitdej1.setOrganisateur(m3);
		
		petitdej2.setDate(date2);
		petitdej2.setName("petitdej3");
		petitdej2.setMembres(mlist);
		petitdej2.setOrganisateur(m2);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			petitdejService.save(petitdej);
			petitdejService.save(petitdej1);
			petitdejService.save(petitdej2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
	
	void inituser(){
		User user=new User("admin", "1234", true, "ROLE_ADMIN");
		User user1=new User("m2", "1234", true, "ROLE_USER");
		User user2=new User("m3", "1234", true, "ROLE_USER");
		try {
			userservice.save(user2);
			userservice.save(user);
			userservice.save(user1);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		
	}
    
	

}
