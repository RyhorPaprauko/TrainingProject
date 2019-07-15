package by.itacademy.database;

import by.itacademy.database.config.DatabaseConfig;
import lombok.Getter;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@Getter
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@Transactional
@Sql("classpath:testDB.sql")
public abstract class BaseTest {
}
