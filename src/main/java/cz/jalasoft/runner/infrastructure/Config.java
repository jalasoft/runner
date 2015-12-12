package cz.jalasoft.runner.infrastructure;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hibernate.cfg.Environment.*;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
@Configuration
public class Config {

    @Bean
    public SessionFactory sessionFactory(DbSetting dbSetting) {
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
    public SessionProvider sessionProvider() {
        return new SessionProvider();
    }
}
