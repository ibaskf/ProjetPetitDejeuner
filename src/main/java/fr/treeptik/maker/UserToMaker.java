package fr.treeptik.maker;

import fr.treeptik.model.User;

public class UserToMaker {

	public static UserMaker To(User user){
		UserMaker userMaker = new UserMaker();
		
		userMaker.setId(user.getId());
		userMaker.setLogin(user.getLogin());
		
		userMaker.setEnabled(user.getEnabled());
		userMaker.setRole(user.getRole());
		
		return userMaker;
	}
	
	
	
}
