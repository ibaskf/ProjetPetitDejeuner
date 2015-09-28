package fr.treeptik.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="petitdej")
public class PetitDej implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	List<Membre> membres;
	
	@Temporal(TemporalType.DATE) 
	private Date date;
	
	@ManyToOne
	private Agenda agenda;
	
	private Double prix;

	@Enumerated(EnumType.STRING)
    private TypeDej type;
	
   private String description;
   
  @OneToMany(mappedBy="petitdej")
   private List<Appreciation> appreciations;
   
  

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public List<Membre> getMembres() {
	return membres;
}

public void setMembres(List<Membre> membres) {
	this.membres = membres;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public Agenda getAgenda() {
	return agenda;
}

public void setAgenda(Agenda agenda) {
	this.agenda = agenda;
}

public Double getPrix() {
	return prix;
}

public void setPrix(Double prix) {
	this.prix = prix;
}

public TypeDej getType() {
	return type;
}

public void setType(TypeDej type) {
	this.type = type;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public List<Appreciation> getAppreciations() {
	return appreciations;
}

public void setAppreciations(List<Appreciation> appreciations) {
	this.appreciations = appreciations;
}

	
	
	
	
	
	

}
