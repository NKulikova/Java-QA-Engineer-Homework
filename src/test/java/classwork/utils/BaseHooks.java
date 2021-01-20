package classwork.utils;

import classwork.config.SpringConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import classwork.pages.LoginPage;
import classwork.pages.MainPageAuth;
import classwork.pages.MainPageNotAuth;

@ContextConfiguration(classes = {SpringConfig.class})
public class BaseHooks {

    @Autowired
    public MainPageNotAuth mainPageNotAuth;

    @Autowired
    public LoginPage loginPage;

    @Autowired
    public MainPageAuth mainPageAuth;

}
