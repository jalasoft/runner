package cz.jalasoft.runner.functional;

import cz.jalasoft.myhealth.Main;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 12/13/15.
 */
@ComponentScan(
        value = {
                "cz.jalasoft.myhealth"
        },
        excludeFilters = {
                @ComponentScan.Filter(pattern = "cz\\.jalasoft\\.myhealth\\.endpoint\\..*", type = FilterType.REGEX),
                @ComponentScan.Filter(value = Main.class, type = FilterType.ASSIGNABLE_TYPE)
        }
)
@Configuration
public class TestConfig {
}
