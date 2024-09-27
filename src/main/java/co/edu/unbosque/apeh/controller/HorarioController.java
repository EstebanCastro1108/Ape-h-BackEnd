package co.edu.unbosque.apeh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.apeh.Auth.Horario;
import co.edu.unbosque.apeh.repository.HorarioRepositoryCRUD;
import co.edu.unbosque.apeh.repository.UserRepositoryCRUD;
import co.edu.unbosque.apeh.repository.model.HorarioModel;
import co.edu.unbosque.apeh.repository.model.HorarioModelDTO;
import co.edu.unbosque.apeh.repository.model.Role;
import co.edu.unbosque.apeh.repository.model.UserModel;

@RestController
@RequestMapping("/horario")
//@CrossOrigin(origins = {"http://d1xd8bdp0146p6.cloudfront.net"})
public class HorarioController {
	@Autowired
	private HorarioRepositoryCRUD horarioRepositoryCRUD;
	
    @Autowired
    private UserRepositoryCRUD userRepositoryCRUD;
	
	@PostMapping(path="/agregar")
	public String agregarMateria(@RequestBody Horario horario) {
		HorarioModel hor = new HorarioModel();
		String aux = "";
        UserModel user = userRepositoryCRUD.findByEmail(horario.getUserThatInserted());
        if (user != null && user.getRole().equals(Role.ADMIN)) {
    		hor.setCode(horario.getCode());
    		hor.setName(horario.getName());
    		hor.setCredits(horario.getCredits());
    		hor.setDay(horario.getDay());
    		hor.setHourStart(horario.getHourStart());
    		hor.setHourFinish(horario.getHourFinish());
    		hor.setModality(horario.getModality());
    		hor.setSchedule(horario.getSchedule());
            hor.setUserThatInserted(user);
    		hor.setStatus(true);
    		horarioRepositoryCRUD.save(hor);
    		aux = "Saved";
        } else {
            aux = "Usuario no existe o no tiene privilegios";
        }
		return aux;
	}
	
	@GetMapping(path="/status")
	public String cambiarEstado() {
		horarioRepositoryCRUD.cambioEstado();
		return "Cambio Exitoso";
	}
	
	@GetMapping(path="/borrar")
	public String borrar() {
		horarioRepositoryCRUD.borrarAnterior();
		return "Se borró con éxito";
	}
	
    @GetMapping("/active")
    public List<HorarioModelDTO> getActiveClasses() {
        return horarioRepositoryCRUD.traerHorario();
    }
}
