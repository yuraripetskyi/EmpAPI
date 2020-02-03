package employees.test.com.services.impl;

import employees.test.com.dao.UserDAO;
import employees.test.com.dto.AuthenticateDTO;
import employees.test.com.entity.User;
import employees.test.com.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public User createUser(AuthenticateDTO authenticateDTO) {
        User user = new User(
                authenticateDTO.getUsername(),
                authenticateDTO.getPassword()
        );
        int id = userDAO.save(user);
        user.setId(id);
        return user;
    }
}
