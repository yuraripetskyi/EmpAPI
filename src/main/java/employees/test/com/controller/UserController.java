package employees.test.com.controller;

import employees.test.com.dto.AuthenticateDTO;
import employees.test.com.dto.JwtDTO;
import employees.test.com.entity.User;
import employees.test.com.services.AuthService;
import employees.test.com.services.UserService;
import employees.test.com.services.validator.RegistrationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final RegistrationValidator registrationValidator;
    private final AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JwtDTO register(
            @RequestBody AuthenticateDTO authenticateDTO
    ) {
        String username = authenticateDTO.getUsername();
        log.debug("Registering new user with username: {}", username);
        registrationValidator.validate(authenticateDTO);
        User user = userService.createUser(authenticateDTO);
        String jwt = authService.authenticateUser(user.getUsername(), user.getPassword());
        return new JwtDTO(jwt);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JwtDTO login(
            @RequestBody AuthenticateDTO authenticateDTO
    ) {
        log.debug("Logging in user with username: {}", authenticateDTO.getUsername());
        String jwt = authService.authenticateUser(authenticateDTO.getUsername(), authenticateDTO.getPassword());
        return new JwtDTO(jwt);
    }
}
