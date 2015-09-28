package fr.treeptik.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.treeptik.model.Membre;
import fr.treeptik.model.PetitDej;


@Repository
public interface PetitDejDAO extends JpaRepository<PetitDej,Integer>{

	List<PetitDej> findByName(String name);
	PetitDej findById(Integer id);
	@Query("SELECT m from Membre m JOIN m.petitdejs p where :id in (p)")
    public List<Membre> find(@Param("id") Integer id);

	@Modifying
	@Query(value="delete from petitdej_membre where membres_id= :idm and petitdejs_id= :id",nativeQuery=true)
	public void removeParticipant(@Param("id") Integer id,@Param("idm") Integer idm);
	
	

}