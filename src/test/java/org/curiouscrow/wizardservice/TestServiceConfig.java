package org.curiouscrow.wizardservice;

import org.curiouscrow.wizardservice.config.TemplateConfigProperties;
import org.curiouscrow.wizardservice.manager.DefaultMustacheTemplateManager;
import org.curiouscrow.wizardservice.manager.TemplateManager;
import org.curiouscrow.wizardservice.providers.FileTemplateProvider;
import org.curiouscrow.wizardservice.providers.TemplateProvider;
import org.curiouscrow.wizardservice.templateinfo.JsonDocumentTemplateDescriptionReader;
import org.curiouscrow.wizardservice.templateinfo.TemplateDescriptionReader;
import org.springframework.context.annotation.Bean;

public class TestServiceConfig {

    @Bean
    TemplateConfigProperties configProperties() {
        TemplateConfigProperties properties = new TemplateConfigProperties();
        properties.setSourcePath("templates");
        return properties;
    }

    @Bean
    TemplateProvider templateProvider() {
        return new FileTemplateProvider();
    }

    @Bean
    TemplateManager templateManager() {
        return new DefaultMustacheTemplateManager();
    }

    @Bean
    TemplateDescriptionReader templateDescriptionReader() {
        return new JsonDocumentTemplateDescriptionReader();
    }
}
