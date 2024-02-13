package org.curiouscrow.wizardservice.controllers;

import org.curiouscrow.wizardservice.config.TemplateConfigProperties;
import org.curiouscrow.wizardservice.config.TemplateServiceConfig;
import org.curiouscrow.wizardservice.entities.TemplateInfo;
import org.curiouscrow.wizardservice.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Basic HTML-service endpoint controller
 */
@Controller
@RequestMapping("/templates")
public class RequestTemplateController {

    private static final Logger logger = Logger.getLogger(RequestTemplateController.class.getName());

    @Autowired
    private TemplateService templateService;

    @Value("${templatewizard.template-form}")
    private String templateForm;

    /** Endpoint for page with template parameters form
     * @param templateName template identificator (e.g. template directory)
     * @return template form mustache page
     * @throws IOException common exception
     * */
    @GetMapping("form/{templateName}")
    public ModelAndView getTemplateForm(@PathVariable("templateName") String templateName) throws IOException {

        Map<String, TemplateInfo> templates = templateService.descriptionReader.readTemplateMap();

        ModelAndView mv = new ModelAndView();
        mv.setViewName(templateForm);
        mv.getModel().put("info", templates.get(templateName));
        return mv;
    }

    /** Endpoint triggering building template artifact filled with form data
     * @param formData html-form custom template parameters
     * @param templateName template identificator (e.g. template directory)
     * @return template form mustache page
     * @throws IOException common exception
     * */
    @PostMapping(path = "/prepare/{templateName}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView prepareTemplateNew(@RequestParam HashMap<String, String> formData, @PathVariable("templateName") String templateName) throws IOException {
        logger.info("Preparing template: " + templateName);

        String templateId = templateService.templateManager.prepareTemplate(templateName, formData);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("projectLink");

        Map<String, TemplateInfo> templates = templateService.descriptionReader.readTemplateMap();
        TemplateInfo templateInfo = templates.get(templateName);

        mv.getModel().put("info", templateInfo);
        mv.getModel().put("templateId", templateId);
        return mv;
    }

    /** Endpoint providing template artifact
     * @param templateName template identificator (e.g. template directory)
     * @return resulting template zip-file
     * @throws IOException common exception
     * */
    @GetMapping(path = "/get/{templateName}", produces = "application/zip")
    public @ResponseBody byte[] getTemplateProjectByName(@PathVariable("templateName") String templateName) throws IOException {
        return Files.readAllBytes(Paths.get(TemplateConfigProperties.ZIPPED_TEMPLATE_FOLDER, templateName + ".zip"));
    }
}