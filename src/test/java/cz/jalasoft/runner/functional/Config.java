package cz.jalasoft.runner.functional;

import cz.jalasoft.runner.Main;
import cz.jalasoft.runner.configuration.DatabaseSetting;
import cz.jalasoft.runner.infrastructure.persistence.DatabaseInitializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.Resource;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/13/15.
 */
@ComponentScan(
        value = {
                "cz.jalasoft.runner"
        },
        excludeFilters = {
                @ComponentScan.Filter(pattern = "cz\\.jalasoft\\.runner\\.infrastructure\\.endpoint\\..*", type = FilterType.REGEX),
                @ComponentScan.Filter(value = Main.class, type = FilterType.ASSIGNABLE_TYPE),
                @ComponentScan.Filter(value = DatabaseSetting.class, type = FilterType.ASSIGNABLE_TYPE)
        }
)
@Configuration
public class Config extends cz.jalasoft.runner.configuration.Config {

        @Value("classpath:clean.sql")
        private Resource cleanScript;

        @Bean
        public DatabaseSetting dbSetting() {
                DatabaseSetting setting = new DatabaseSetting();

                setting.setDriver("org.h2.Driver");
                setting.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
                setting.setUsername("");
                setting.setPassword("");
                setting.setPoolSize(4);

                return setting;
        }

        @Bean
        public DatabaseInitializer initializer(DatabaseSetting setting) {
             return new DatabaseInitializer(cleanScript, setting);
        }
}
