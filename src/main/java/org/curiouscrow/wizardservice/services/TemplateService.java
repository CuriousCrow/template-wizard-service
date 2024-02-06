package org.curiouscrow.wizardservice.services;

import org.curiouscrow.wizardservice.config.TemplateConfigProperties;
import org.curiouscrow.wizardservice.manager.TemplateManager;
import org.curiouscrow.wizardservice.providers.TemplateProvider;
import org.curiouscrow.wizardservice.templateinfo.TemplateDescriptionReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class TemplateService {

    private static final Logger logger = Logger.getLogger(TemplateService.class.getName());

    @Autowired
    public TemplateDescriptionReader descriptionReader;

    @Autowired
    public TemplateProvider templateProvider;

    @Autowired
    public TemplateManager templateManager;

    @Autowired
    public TemplateConfigProperties properties;

}
