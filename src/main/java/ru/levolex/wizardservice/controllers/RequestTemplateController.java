package ru.levolex.wizardservice.controllers;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.levolex.wizardservice.entities.TemplateInfo;
import ru.levolex.wizardservice.services.TemplateService;
import ru.levolex.wizardservice.utils.TemplateItem;
import ru.levolex.wizardservice.utils.TemplateUtils;
import ru.levolex.wizardservice.utils.ZipDirectory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class RequestTemplateController {

    private static final Logger logger = Logger.getLogger(RequestTemplateController.class.getName());

    private static final String DIR_RESULT = "results";
    private static final String DIR_OUTPUT_BASE = "out";
    private static final String DIR_TEMPLATES = "templates";

    @Autowired
    private TemplateService templateService;

    @GetMapping("/templates")
    @ResponseBody
    public String getTemplateList() {
        return "<ul><li>First template</li><li>Second template</li><li>Third template</li></ul>";
    }

    @GetMapping("/templates/form/{templateName}")
    public ModelAndView getTemplateForm(@PathVariable String templateName) throws IOException {

        Map<String, TemplateInfo> templates = templateService.readTemplateMap();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("templateForm");
        mv.getModel().put("info", templates.get(templateName));
        return mv;
    }

    @PostMapping(path = "/templates/prepare/{templateName}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView prepareTemplateNew(@RequestParam HashMap<String, String> formData, @PathVariable String templateName) throws IOException {
        logger.info("Template name: " + templateName);
        logger.info("Template params: " + formData);

        TemplateService templateService = new TemplateService();
        templateService.prepareTemplate(templateName, formData);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("projectLink");

        Map<String, TemplateInfo> templates = templateService.readTemplateMap();
        TemplateInfo templateInfo = templates.get(templateName);

        mv.getModel().put("info", templateInfo);
        return mv;
    }

    @GetMapping(path = "/templates/get/{templateName}", produces = "application/zip")
    public @ResponseBody
    byte[] getTemplateProjectByName(@PathVariable String templateName) throws IOException {
        return Files.readAllBytes(Paths.get(DIR_RESULT, templateName + ".zip"));
    }
}