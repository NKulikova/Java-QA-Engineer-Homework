package otus.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.DirtiesContext;

@Configuration
@ConfigurationProperties(ignoreInvalidFields = true)
@ComponentScan(basePackages = "otus")
@PropertySource(value = "classpath:properties/application.properties")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class PropertiesConfig {
}
