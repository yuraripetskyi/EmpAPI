package employees.test.com.services.impl;

import employees.test.com.dao.UserDAO;
import employees.test.com.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service("UserDetailsServiceImpl")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        employees.test.com.entity.User userByUsername = userDAO.getUserByUsername(username);
        return new User(
                userByUsername.getUsername(),
                userByUsername.getPassword(),
                new ArrayList<>()
        );
    }
}
