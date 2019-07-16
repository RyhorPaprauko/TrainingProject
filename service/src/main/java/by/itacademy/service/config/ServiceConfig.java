package by.itacademy.service.config;

import by.itacademy.database.config.DatabaseConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("by.itacademy.service.service")
@EnableTransactionManagement
@Import(DatabaseConfig.class)
public class ServiceConfig {
}
