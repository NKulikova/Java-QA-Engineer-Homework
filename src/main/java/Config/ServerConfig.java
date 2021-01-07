package Config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.*;

@Sources("classpath:config.properties")
public interface ServerConfig extends Config {

    @Key("url")
    String url();
    @Key("login")
    String login();
    @Key("password")
    String password();
    @Key("browser")
    String browser();

}
