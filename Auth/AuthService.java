package co.edu.unbosque.apeh.Auth;


import co.edu.unbosque.apeh.Jwt.JwtService;
import co.edu.unbosque.apeh.controller.UserController;
import co.edu.unbosque.apeh.repositories.UserRepository;
import co.edu.unbosque.apeh.repositories.models.Role;
import co.edu.unbosque.apeh.repositories.models.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserController userC;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        UserModel user = UserModel.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.lastname)
                .country(request.getCountry())
                .role(Role.USER)
                .build();

        userC.guardarUser(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }




}
