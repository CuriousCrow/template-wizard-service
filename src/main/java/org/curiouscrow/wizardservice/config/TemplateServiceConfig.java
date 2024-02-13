package org.curiouscrow.wizardservice.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main library configuration class
 * (should be enabled by annotation @EnableTemplateWizardService)
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"org.curiouscrow.wizardservice"})
@EnableConfigurationProperties(TemplateConfigProperties.class)
public class TemplateServiceConfig {
}
