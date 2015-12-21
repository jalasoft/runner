package cz.jalasoft.runner.configuration;

import cz.jalasoft.runner.domain.model.run.RunRepository;
import cz.jalasoft.runner.domain.model.runner.RunnerRepository;
import cz.jalasoft.runner.domain.model.service.RunningStatisticsService;
import cz.jalasoft.runner.infrastructure.persistence.DatabaseInitializer;
import cz.jalasoft.runner.infrastructure.persistence.SessionProvider;
import cz.jalasoft.runner.infrastructure.persistence.repository.HibernateRunRepository;
import cz.jalasoft.runner.infrastructure.persistence.repository.HibernateRunnerRepository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import static org.hibernate.cfg.Environment.*;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
@Configuration
public class Config {

    //@Value("classpath:init.sql")
    //private Resource initDb;

    @Bean
    public SessionFactory sessionFactory(DatabaseSetting dbSetting) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()

                .applySetting(DRIVER, dbSetting.getDriver())
                .applySetting(URL, dbSetting.getUrl())
                .applySetting(USER, dbSetting.getUsername())
                .applySetting(PASS, dbSetting.getPassword())
                .applySetting(POOL_SIZE, dbSetting.getPoolSize())

                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addResource("Runner.hbm.xml")
                .addResource("Run.hbm.xml")

                .buildMetadata();

        SessionFactory sessionFactory = metadata.buildSessionFactory();

        return sessionFactory;
    }

    @Bean
    public SessionProvider sessionProvider(
            DatabaseSetting setting,
            @Value("classpath:init.sql")
            Resource initDb) {

        SessionProvider provider = new SessionProvider();

        new DatabaseInitializer(initDb, setting).initialize();

        return provider;
    }



    @Bean
    public RunningStatisticsService runningStatisticsService() {
        return new RunningStatisticsService();
    }

    @Bean
    public RunnerRepository runnerRepository(SessionProvider sessionProvider) {
        return new HibernateRunnerRepository(sessionProvider);
    }

    @Bean
    public RunRepository runRepository(SessionProvider sessionProvider) {
        return new HibernateRunRepository(sessionProvider);
    }
}
