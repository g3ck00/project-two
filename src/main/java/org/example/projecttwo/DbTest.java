package org.example.projecttwo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbTest implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DbTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        var result = jdbcTemplate.queryForList("SELECT * FROM usuario");
        System.out.println(result);
    }
}
