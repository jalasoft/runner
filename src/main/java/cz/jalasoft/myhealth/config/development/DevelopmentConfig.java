package cz.jalasoft.myhealth.config.development;

import cz.jalasoft.myhealth.config.DatabaseSetting;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * @author Honza Lastovicka (lastovicka@avast.com)
 * @since 2016-10-05.
 */
@Profile("dev")
@Configuration
@EnableWebMvc
public class DevelopmentConfig extends WebMvcConfigurerAdapter {

    @Value("classpath:init.sql")
    private Resource initScript;

    @Primary
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);

        String dir = System.getProperty("user.dir");
        String path = "file:" + dir + "/client/";

       registry.addResourceHandler("/**")
                .addResourceLocations(path)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());

    }

    @EventListener
    public void initialize(ContextRefreshedEvent event) {
        new DatabaseInitializer(dbSetting(), initScript).initialize();
    }
}
