package co.edu.unbosque.apeh.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.apeh.repository.model.Role;
import co.edu.unbosque.apeh.repository.model.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import java.security.SecureRandom;
import java.util.Random;
@Repository
public class UserRepositoryCRUD{
	
    @Autowired
    private MailService emailService;
	
    @Autowired
    private UserRepository userRepository;
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 12; // Length of the generated password
    
	public UserModel userByID(String id) {

		return null;
	}

	public Optional<UserModel> findByUsername(String username) {

		return Optional.empty();
	}

    public String changePassword(UserModel user) {
    	String res = "";
        if (user != null) {
            Random random = new SecureRandom();
            StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
            for (int i = 0; i < PASSWORD_LENGTH; i++) {
                int index = random.nextInt(CHARACTERS.length());
                password.append(CHARACTERS.charAt(index));
            }
            String NuevaContraseña = password.toString();
            user.setContraseña(NuevaContraseña);
            userRepository.save(user);
            emailService.sendPasswordChangeNotification(user.getEmail(), NuevaContraseña);
            res = "Cambio exitoso";
        } else {
            res = "Usuario no encontrado";
        }
        return res;
    }
	
    public void crearUsuario(String email, String username, String nombre, String apellido, String contraseña, Integer role) {
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setUser(username);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setContraseña(contraseña);
        user.setRole(Role.USER);
        userRepository.save(user);
    }
    
    public void modificarUsuario(String username, String nombre, String apellido, String newPassword, UserModel user) {
        user.setUser(username);
        user.setNombre(nombre);
        user.setApellido(apellido);
        user.setContraseña(newPassword);
        userRepository.save(user);
    }
    
    public boolean login(String email, String password) {
    	Optional<UserModel> user = userRepository.findByEmail(email);
    	return user.isPresent() && user.get().getPassword().equals(password);
    }
    
    public String getRole(String email) {
    	Optional<UserModel> user = userRepository.findByEmail(email);
    	String aux = ""+user.get().getRole();
    	return aux;
    }
    
    public UserModel findByEmail(String email) {
    	Optional<UserModel> user = userRepository.findByEmail(email);
    	return user.get();
    }
}
