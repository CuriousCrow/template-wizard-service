package org.curiouscrow.wizardservice.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.logging.Logger;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"org.curiouscrow.wizardservice"})
@EnableConfigurationProperties(TemplateConfigProperties.class)
public class TemplateServiceConfig {

    private static final Logger logger = Logger.getLogger(TemplateServiceConfig.class.getName());

    public TemplateServiceConfig() {
        logger.info("Template wizard config is created!");
    }

}
