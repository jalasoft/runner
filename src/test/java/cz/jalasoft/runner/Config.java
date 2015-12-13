package cz.jalasoft.runner;

import cz.jalasoft.runner.infrastructure.config.DbSetting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

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
                @ComponentScan.Filter(value = Main.class, type = FilterType.ASSIGNABLE_TYPE)
        }
)
@Configuration
public class Config extends cz.jalasoft.runner.infrastructure.config.Config {

        @Bean
        public DbSetting dbSetting() {
                DbSetting setting = new DbSetting();

                setting.setDriver("org.h2.Driver");
                setting.setUrl("jdbc:h2:mem:test");
                setting.setUsername("");
                setting.setPassword("");
                setting.setPoolSize(4);

                return setting;
        }
}
