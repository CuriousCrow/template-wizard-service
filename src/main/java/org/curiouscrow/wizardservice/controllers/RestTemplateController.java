package org.curiouscrow.wizardservice.controllers;

import org.curiouscrow.wizardservice.entities.TemplateInfo;
import org.curiouscrow.wizardservice.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * REST API endpoints
 * enabled by application property templatewizard.api.enabled
 */
@RestController
@RequestMapping("/api/templates")
@ConditionalOnProperty(prefix = "templatewizard.api", name = "enabled", havingValue = "true")
public class RestTemplateController {

    private static final Logger logger = Logger.getLogger(RestTemplateController.class.getName());

    @Autowired
    private TemplateService templateService;

    @GetMapping
    public List<TemplateInfo> getAll() throws IOException {
        return templateService.descriptionReader.readTemplateStructure();
    }

}
