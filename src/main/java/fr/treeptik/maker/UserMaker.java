package fr.treeptik.maker;

import java.io.Serializable;

public class UserMaker implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String login;
	private String password;
	private String passwordMatch;

	private Boolean enabled;
	private String role;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getPasswordMatch() {
		return passwordMatch;
	}
	public void setPasswordMatch(String passwordMatch) {
		this.passwordMatch = passwordMatch;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
