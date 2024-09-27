package co.edu.unbosque.apeh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.apeh.Auth.LoginRequest;
import co.edu.unbosque.apeh.Auth.RegisterRequest;
import co.edu.unbosque.apeh.repository.UserRepository;
import co.edu.unbosque.apeh.repository.UserRepositoryCRUD;
import co.edu.unbosque.apeh.repository.model.Role;
import co.edu.unbosque.apeh.repository.model.UserModel;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/authent")
//@CrossOrigin(origins = {"http://d1xd8bdp0146p6.cloudfront.net"})
public class UserController {
    @Autowired
    private UserRepositoryCRUD userRepositoryCRUD;

	@PostMapping(path="/register")
	public String guardarUser (@RequestBody RegisterRequest request) {
    	boolean res = userRepositoryCRUD.login(request.getEmail(), request.getPassword());
    	String resS = "";
    	if(res == false){
		UserModel n = new UserModel();
		n.setNombre(request.getFirstname());
		n.setEmail(request.getEmail());
		n.setApellido(request.getLastname());
		n.setContraseña(request.getPassword());
		n.setUser(request.getUsername());
		n.setRole(Role.USER);
		userRepositoryCRUD.crearUsuario(n.getEmail(), n.getUsername(), n.getNombre(), n.getApellido(), n.getContraseña(), n.IsRole());
		resS = "Saved";
    	}
    	else if(res == true) {
    	resS = "El usuario ya existe";
    	}
    	return resS;
	}
    @GetMapping("/message")
    public String getMessage() {
        return "Hello from Spring Boot!";
    }
    @PostMapping(path="/login")
    public String login(@RequestBody LoginRequest login) {
    	boolean res = userRepositoryCRUD.login(login.getUsername(), login.getPassword());
    	return ""+res;
    }
	public String guardarUser(UserModel n) {
		userRepositoryCRUD.crearUsuario(n.getEmail(), n.getUsername(), n.getNombre(), n.getApellido(), n.getContraseña(), n.IsRole());
		return "Saved";
	}

	@PostMapping(path="/role")
	public String getRole(@RequestBody LoginRequest login) {
		return userRepositoryCRUD.getRole(login.getUsername());
	}
	
	@PostMapping(path="/modificar")
	public String modificar (@RequestBody RegisterRequest request) {
        UserModel user = userRepositoryCRUD.findByEmail(request.getEmail());
        String resS = "";
        if (user != null) {
		userRepositoryCRUD.modificarUsuario(request.getUsername(), request.getFirstname(), request.getLastname(), request.getPassword() ,user);
		resS = "Saved";
    	}
    	else {
    	resS = "El usuario ya existe";
    	}
    	return resS;
	}
	
	@PostMapping(path="/password")
	public String modificarContraseña (@RequestBody LoginRequest request) {
		UserModel user = userRepositoryCRUD.findByEmail(request.getUsername());
		String res = userRepositoryCRUD.changePassword(user);
		return res;
	}
	
}
