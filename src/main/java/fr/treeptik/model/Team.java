package fr.treeptik.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="team")
public class Team implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="team")
	private List<Membre> membres;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="team")
	private List<PetitDej> petitdejs;
	
	
	@OneToOne
	private Membre responsable;
	

	public Membre getResponsable() {
		return responsable;
	}

	public void setResponsable(Membre responsable) {
		this.responsable = responsable;
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

	public List<Membre> getMembres() {
		return membres;
	}

	public void setMembres(List<Membre> membres) {
		this.membres = membres;
	}

	public List<PetitDej> getPetitdejs() {
		return petitdejs;
	}

	public void setPetitdejs(List<PetitDej> petitdejs) {
		this.petitdejs = petitdejs;
	}
	




}
