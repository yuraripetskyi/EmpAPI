package employees.test.com.dao;

import employees.test.com.entity.User;

public interface UserDAO {
    int save(User user);
    boolean isExist(User user);

    User getUserByUsername(String username);
}
