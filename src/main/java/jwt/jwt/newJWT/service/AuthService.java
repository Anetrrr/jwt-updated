package jwt.jwt.newJWT.service;

import jwt.jwt.newJWT.model.User;
import jwt.jwt.newJWT.repository.UserRepository;
import jwt.jwt.newJWT.request.AuthenticateRequest;
import jwt.jwt.newJWT.request.RegisterRequest;
import jwt.jwt.newJWT.response.AuthenticationResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Data
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();

        var registeredUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(registeredUser);
      return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    public AuthenticationResponse authenticate (AuthenticateRequest authenticateRequest){

        //1. validate request(validate whether username/password is correct) = UserNamePassword AuthenticationToken
        //2. check if user is present in db
        //3. authenticationProvider -> DaoAuthenticationProvider (Inject) = check applicationConfig
        //4. need to authenticate using authentication manager = check applicationConfig


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticateRequest.getEmail(), authenticateRequest.getPassword())
        );

        var user = userRepository.findByEmail(authenticateRequest.getEmail()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();

    }
}
