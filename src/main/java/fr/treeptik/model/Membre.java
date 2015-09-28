package fr.treeptik.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="membre")
public class Membre implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

private String name;
private String firstname;

@ManyToOne 
private Team team;



@ManyToMany(mappedBy="membres")
private List<PetitDej> petitdejs;

@Enumerated(EnumType.STRING)	
private TypeDej preference;


@Column(name = "login")
private String login;


private String  password;

@Column(name = "enabled")
private Boolean enabled;

@Column(name = "role")
private String role;




public String getLogin() {
	return login;
}

public void setLogin(String login) {
	this.login = login;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Boolean getEnabled() {
	return enabled;
}

public void setEnabled(Boolean enabled) {
	this.enabled = enabled;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public List<PetitDej> getPetitdejs() {
	return petitdejs;
}

public void setPetitdejs(List<PetitDej> petitdejs) {
	this.petitdejs = petitdejs;
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

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}


	
	

}
