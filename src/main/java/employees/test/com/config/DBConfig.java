package employees.test.com.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Configuration
@AllArgsConstructor
public class DBConfig {
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void initDb(){
        Resource resource = new ClassPathResource("/scripts/init.sql");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(Objects.requireNonNull(jdbcTemplate.getDataSource()));
    }
}
