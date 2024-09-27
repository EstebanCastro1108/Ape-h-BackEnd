package co.edu.unbosque.apeh.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.apeh.repository.model.HorarioModel;
import co.edu.unbosque.apeh.repository.model.HorarioModelDTO;
import co.edu.unbosque.apeh.repository.model.UserModel;
@Repository
public class HorarioRepositoryCRUD {

	@Autowired
	private HorarioRepository horarioRepository;
	
    public HorarioModel save(HorarioModel course) {
        return horarioRepository.save(course);
    }

    public List<HorarioModelDTO> traerHorario() {
    	return horarioRepository.findByStatus(true).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
	
    public void cambioEstado() {
    	horarioRepository.cambiarAFalso();
    }
    
    public void borrarAnterior() {
    	horarioRepository.borrarHorarioAnterior();
    }
    
    private HorarioModelDTO convertToDTO(HorarioModel horarioModel) {
        HorarioModelDTO dto = new HorarioModelDTO();
        dto.setCode(horarioModel.getCode());
        dto.setName(horarioModel.getName());
        dto.setCredits(horarioModel.getCredits());
        dto.setDay(horarioModel.getDay());
        dto.setHourStart(horarioModel.getHourStart());
        dto.setHourFinish(horarioModel.getHourFinish());
        dto.setModality(horarioModel.getModality());
        dto.setSchedule(horarioModel.getSchedule());

        return dto;
    }
}
