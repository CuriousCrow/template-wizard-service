package org.curiouscrow.wizardservice.controllers;

import org.curiouscrow.wizardservice.config.TemplateConfigProperties;
import org.curiouscrow.wizardservice.config.TemplateServiceConfig;
import org.curiouscrow.wizardservice.entities.TemplateInfo;
import org.curiouscrow.wizardservice.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/templates")
public class RequestTemplateController {

    private static final Logger logger = Logger.getLogger(RequestTemplateController.class.getName());

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateConfigProperties properties;

    @GetMapping("/test")
    @ResponseBody
    public String getRequestForTestEndpoint() {
        String responseStr = "<html><head></head><body>Hello from template path <b>%s</b></body></html>";
        return String.format(responseStr, properties.getSourcePath());
    }

    @GetMapping("form/{templateName}")
    public ModelAndView getTemplateForm(@PathVariable String templateName) throws IOException {

        Map<String, TemplateInfo> templates = templateService.descriptionReader.readTemplateMap();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("templateForm");
        mv.getModel().put("info", templates.get(templateName));
        return mv;
    }

    @PostMapping(path = "/prepare/{templateName}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView prepareTemplateNew(@RequestParam HashMap<String, String> formData, @PathVariable String templateName) throws IOException {
        logger.info("Template name: " + templateName);
        logger.info("Template params: " + formData);

        String templateId = templateService.templateManager.prepareTemplate(templateName, formData);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("projectLink");

        Map<String, TemplateInfo> templates = templateService.descriptionReader.readTemplateMap();
        TemplateInfo templateInfo = templates.get(templateName);

        mv.getModel().put("info", templateInfo);
        mv.getModel().put("templateId", templateId);
        return mv;
    }

    @GetMapping(path = "/get/{templateName}", produces = "application/zip")
    public @ResponseBody byte[] getTemplateProjectByName(@PathVariable String templateName) throws IOException {
        return Files.readAllBytes(Paths.get(TemplateConfigProperties.ZIPPED_TEMPLATE_FOLDER, templateName + ".zip"));
    }
}