package co.edu.unbosque.apeh.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.apeh.repository.model.HorarioElectivoModel;
import co.edu.unbosque.apeh.repository.model.HorarioElectivoModelDTO;
@Repository
public class HorarioElectivoRepositoryCRUD {

	@Autowired
	private HorarioElectivoRepository horarioElectivoRepository;
	
    public HorarioElectivoModel save(HorarioElectivoModel course) {
        return horarioElectivoRepository.save(course);
    }

    public List<HorarioElectivoModelDTO> traerHorario() {
    	return horarioElectivoRepository.findByStatus(true).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
	
    public void cambioEstado() {
    	horarioElectivoRepository.cambiarAFalso();
    }
    
    public void borrarAnterior() {
    	horarioElectivoRepository.borrarHorarioAnterior();
    }
    
    private HorarioElectivoModelDTO convertToDTO(HorarioElectivoModel horarioElectivoModel) {
        HorarioElectivoModelDTO dto = new HorarioElectivoModelDTO();
        dto.setCode(horarioElectivoModel.getCode());
        dto.setName(horarioElectivoModel.getName());
        dto.setCredits(horarioElectivoModel.getCredits());
        dto.setDay(horarioElectivoModel.getDay());
        dto.setHourStart(horarioElectivoModel.getHourStart());
        dto.setHourFinish(horarioElectivoModel.getHourFinish());
        dto.setModality(horarioElectivoModel.getModality());

        return dto;
    }
}
