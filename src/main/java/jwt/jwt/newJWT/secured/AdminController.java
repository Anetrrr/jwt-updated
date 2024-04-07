package jwt.jwt.newJWT.secured;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('Admin')")
public class AdminController {


    @GetMapping
    public String getAdmin() {
        return "Secured Endpoint :: GET - Admin controller";
    }


}
