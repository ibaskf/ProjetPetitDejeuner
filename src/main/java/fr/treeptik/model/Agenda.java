package fr.treeptik.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="agenda")
public class Agenda implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	

	
	@OneToMany(mappedBy="agenda")
	private List<PetitDej> petitdej;
	
	
   public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public List<PetitDej> getPetitdej() {
		return petitdej;
	}


	public void setPetitdej(List<PetitDej> petitdej) {
		this.petitdej = petitdej;
	}


	
	
	

}
