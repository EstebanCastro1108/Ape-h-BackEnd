package co.edu.unbosque.apeh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.apeh.repository.model.HorarioElectivoModel;
import jakarta.transaction.Transactional;

@Repository
public interface HorarioElectivoRepository extends JpaRepository<HorarioElectivoModel, Long> {

	List<HorarioElectivoModel> findByStatus(boolean status);
	
    @Modifying
    @Transactional
    @Query("UPDATE HorarioElectivoModel h SET h.status = false WHERE h.status = true")
    void cambiarAFalso();
    
    @Modifying
    @Transactional
    @Query("DELETE FROM HorarioElectivoModel h WHERE h.status = false")
    void borrarHorarioAnterior();
}
