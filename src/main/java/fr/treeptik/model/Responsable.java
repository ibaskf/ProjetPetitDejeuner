package fr.treeptik.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Responsable extends Membre {

	@OneToMany(mappedBy = "manager")
	private List<Membre> membres;

	public List<Membre> getEmployees() {
		return membres;
	}

	public void setEmployees(List<Membre> membres) {
		this.membres = membres;
	}

}