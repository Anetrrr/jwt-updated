package jwt.jwt.newJWT.controller;


import jwt.jwt.newJWT.request.AuthenticateRequest;
import jwt.jwt.newJWT.request.RegisterRequest;
import jwt.jwt.newJWT.response.AuthenticationResponse;
import jwt.jwt.newJWT.service.AuthService;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")

@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (@RequestBody RegisterRequest registerRequest){


        AuthenticationResponse authResponse = authService.register(registerRequest);
        return ResponseEntity.ok(authResponse);


    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (@RequestBody AuthenticateRequest authenticateRequest){

        //check if the user exists
        return null;



    }


}
