package co.edu.unbosque.apeh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.apeh.repository.model.UserModel;
import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuario (email, username, nombre, apellido, contraseña, role) VALUES (:email, :username, :nombre, :apellido, :contraseña, :role)", nativeQuery = true)
    void crearUsuario(@Param("email") String email, @Param("username") String username, @Param("nombre") String nombre, @Param("apellido") String apellido, @Param("contraseña") String contraseña, @Param("role") Integer role);
}
