package otus.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "otus")
@PropertySource(value = "classpath:properties/application.properties")
public class PropertiesConfig {
}
