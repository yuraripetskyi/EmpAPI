package employees.test.com.dao.impl;

import employees.test.com.dao.UserDAO;
import employees.test.com.entity.User;
import employees.test.com.exception.RegistrationException;
import employees.test.com.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class UserDAOImpl implements UserDAO {
    private static final String SELECT_USER_WHERE_USERNAME = "select * from tblUsers where usrName = ?";
    private static final String SELECT_USER_WHERE_ID = "select * from tblUsers where usrID = ?";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final UserMapper userMapper;

    public UserDAOImpl(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        // init SimpleJdbcInsert
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        this.simpleJdbcInsert.setTableName("tblUsers");
        this.simpleJdbcInsert.setGeneratedKeyName("empID");

        this.userMapper = userMapper;
    }

    @Override
    public int save(User user) {
        if (isExist(user)) {
            throw new RegistrationException(String.format("User with username %s already exists", user.getUsername()));
        } else {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("usrName", user.getUsername());
            parameters.put("usrPassword", user.getPassword());
            return simpleJdbcInsert.executeAndReturnKey(parameters).intValue();
        }
    }

    @Override
    public boolean isExist(User user) {
        return nonNull(getUserByUsername(user.getUsername()));
    }

    /**
     * @return null if user not found
     */
    @Override
    public User getUserByUsername(String username) {
        User fromDB = null;
        try {
            fromDB = jdbcTemplate.queryForObject(SELECT_USER_WHERE_USERNAME, new Object[]{username}, userMapper);
        } catch (EmptyResultDataAccessException e) {
            log.debug("User with username {} not exist", username);
        }
        return fromDB;
    }
}
