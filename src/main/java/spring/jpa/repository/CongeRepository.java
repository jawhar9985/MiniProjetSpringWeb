package spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.jpa.model.Conge;

public interface CongeRepository extends JpaRepository<Conge, Long> {

	List<Conge> findByEmployeId(Long employeId);


	List<Conge> findByEtat(String etat);

	
	
    @Query("SELECT c FROM Conge c WHERE YEAR(c.dateDebut) = :year")
    List<Conge> findByYear(@Param("year") int year);
    
    @Query("SELECT c FROM Conge c WHERE c.etat = :etat AND YEAR(c.dateDebut) = :year")
    List<Conge> findByEtatAndYear(@Param("etat") String etat, @Param("year") int year);

}
