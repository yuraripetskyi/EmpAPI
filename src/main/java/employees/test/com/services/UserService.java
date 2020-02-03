package employees.test.com.services;

import employees.test.com.dto.AuthenticateDTO;
import employees.test.com.entity.User;

public interface UserService {
    User createUser(AuthenticateDTO authenticateDTO);
}
