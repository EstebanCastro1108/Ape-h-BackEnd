package co.edu.unbosque.apeh.repository.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class UserModel implements UserDetails{
	
	@Id
	private String email;
	
	private String contraseña;

	private String nombre;
	
	private String apellido;

	private Role role;

	private String username;
	
    @OneToMany(mappedBy = "userThatInserted")
    private Set<HorarioModel> Horario;
	
    @OneToMany(mappedBy = "userThatInserted")
    private Set<HorarioElectivoModel> HorarioE;
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public String getUser() {
		return username;
	}

	public void setUser(String username) {
		this.username = username;
	}

	public void builder(String username1, String encode, String firstname, String lastname, String email,Role i) {
		username = username1;
		contraseña = encode;
		nombre = firstname;
		apellido = lastname;
		this.email = email;
		role = i;
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return contraseña;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	public Integer IsRole(){
		Integer aux = 0;
		if (role == Role.ADMIN){
			aux = 2;
		}
		else if (role == Role.USER){
			aux = 1;
		}
		return aux;
	}
}
