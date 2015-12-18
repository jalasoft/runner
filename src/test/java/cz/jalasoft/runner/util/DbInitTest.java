package cz.jalasoft.runner.util;

import cz.jalasoft.runner.configuration.DatabaseSetting;
import cz.jalasoft.runner.infrastructure.DatabaseInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/18/15.
 */

public class DbInitTest {

    @Test
    public void testik() {
        DatabaseSetting setting = dbSetting();

        new DatabaseInitializer(new ClassPathResource("init.sql"), setting).initialize();

        JdbcTemplate template = new JdbcTemplate(dataSource(dbSetting()));

        List<String> nicknames = template.query("select * from runner", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("nickname");
            }
        });

        System.out.println(nicknames);
    }
/*
    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("init.sql"));
        return populator;
    }
*/

    private DataSource dataSource(DatabaseSetting setting) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(setting.getDriver());
        dataSource.setUrl(setting.getUrl());
        dataSource.setUsername(setting.getUsername());
        dataSource.setPassword(setting.getPassword());

        return dataSource;
    }

    private DatabaseSetting dbSetting() {
        DatabaseSetting setting = new DatabaseSetting();

        setting.setDriver("org.h2.Driver");
        setting.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        setting.setUsername("");
        setting.setPassword("");
        setting.setPoolSize(4);

        return setting;
    }
}
