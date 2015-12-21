package cz.jalasoft.runner.infrastructure.persistence;

import cz.jalasoft.runner.configuration.DatabaseSetting;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;


/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/18/15.
 */
public final class DatabaseInitializer {

    private final DatabaseSetting dbSetting;
    private final Resource initScript;

    public DatabaseInitializer(Resource initScript, DatabaseSetting dbSetting) {
        this.initScript = initScript;
        this.dbSetting = dbSetting;
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
