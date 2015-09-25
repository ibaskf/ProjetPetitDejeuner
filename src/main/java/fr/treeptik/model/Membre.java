package fr.treeptik.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Membre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

private String name;

@ManyToOne 
private Team team;



@ManyToOne
private PetitDej petitdej;


private TypeDej preference;





public PetitDej getPetitdej() {
	return petitdej;
}

public void setPetitdej(PetitDej petitdej) {
	this.petitdej = petitdej;
}

public TypeDej getPreference() {
	return preference;
}

public void setPreference(TypeDej preference) {
	this.preference = preference;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Team getTeam() {
	return team;
}

public void setTeam(Team team) {
	this.team = team;
}


	
	

}
