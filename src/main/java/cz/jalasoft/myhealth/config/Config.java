package cz.jalasoft.myhealth.config;

import cz.jalasoft.myhealth.domain.model.run.RunRepository;
import cz.jalasoft.myhealth.domain.model.user.UserRepository;
import cz.jalasoft.myhealth.infrastructure.persistence.SessionHolder;
import cz.jalasoft.myhealth.infrastructure.persistence.repository.HibernateRunRepository;
import cz.jalasoft.myhealth.infrastructure.persistence.repository.HibernateUserRepository;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

import static org.hibernate.cfg.Environment.*;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/11/15.
 */
@EnableAspectJAutoProxy
@Configuration
public class Config {

    @Bean
    public UserRepository runnerRepository(SessionHolder sessionProvider) {
        return new HibernateUserRepository(sessionProvider);
    }

    @Bean
    public RunRepository runRepository(SessionHolder sessionProvider) {
        return new HibernateRunRepository(sessionProvider);
    }

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
                .addResource("User.hbm.xml")
                .addResource("Run.hbm.xml")

                .buildMetadata();

        SessionFactory sessionFactory = metadata.buildSessionFactory();

        return sessionFactory;
    }

    @Bean
    public SessionHolder sessionProvider() {
        return new SessionHolder();
    }


}
