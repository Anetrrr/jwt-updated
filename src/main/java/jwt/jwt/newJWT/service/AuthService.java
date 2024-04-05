package jwt.jwt.newJWT.service;

import jwt.jwt.newJWT.model.User;
import jwt.jwt.newJWT.repository.UserRepository;
import jwt.jwt.newJWT.request.RegisterRequest;
import jwt.jwt.newJWT.response.AuthenticationResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Data
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .role(registerRequest.getRole())
                .build();

        var registeredUser = userRepository.save(user);
        String jwtToken = jwtService.generateToken(registeredUser);
      return AuthenticationResponse.builder().accessToken(jwtToken).build();




    }
}
