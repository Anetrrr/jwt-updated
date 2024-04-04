package jwt.jwt.newJWT.model;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="_user")
public class User {


    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String firstName;
    private String lastName;

    private String email;

    private String password;

    private Role role;

}
