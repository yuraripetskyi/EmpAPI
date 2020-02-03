package employees.test.com.services.impl;

import employees.test.com.exception.LoginException;
import employees.test.com.services.AuthService;
import employees.test.com.utiils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {
    public static final String AUTH_HEADER = "auth-jwt";

    private final AuthenticationManager authenticationManager;
    @Qualifier("UserDetailsServiceImpl")
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public String authenticateUser(String username, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (BadCredentialsException e) {
            throw new LoginException("Incorrect username of password");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }
}
