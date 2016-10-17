package cz.jalasoft.myhealth.config.development;

import cz.jalasoft.myhealth.config.DatabaseSetting;
import cz.jalasoft.myhealth.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/18/15.
 */
final class DatabaseInitializer {

    private final DatabaseSetting dbSetting;
    private final Resource initScript;

    DatabaseInitializer(DatabaseSetting dbSetting, Resource initScript) {
        this.dbSetting = dbSetting;
        this.initScript = initScript;
    }

    public void initialize() {
        DataSource dataSource = dataSource(dbSetting);
        DatabasePopulatorUtils.execute(databasePopulator(), dataSource);
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(initScript);
        return populator;
    }

    private DataSource dataSource(DatabaseSetting setting) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(setting.getDriver());
        dataSource.setUrl(setting.getUrl());
        dataSource.setUsername(setting.getUsername());
        dataSource.setPassword(setting.getPassword());

        return dataSource;
    }
}
