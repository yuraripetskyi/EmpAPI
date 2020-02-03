package employees.test.com.mappers;

import employees.test.com.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("usrID"));
        user.setUsername(resultSet.getString("usrName"));
        user.setPassword(resultSet.getString("usrPassword"));
        return user;
    }
}
