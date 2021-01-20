package com.spring;

import com.spring.services.A;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner {

    private static Logger logger = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        Runner app = (Runner) ctx.getBean(Runner.class);
        app.run();
    }

    @Autowired
    A a;

    public void run() {
        logger.info(a.returnCName());
    };

}
