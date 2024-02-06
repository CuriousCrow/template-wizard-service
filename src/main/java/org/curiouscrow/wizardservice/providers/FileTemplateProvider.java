package org.curiouscrow.wizardservice.providers;

import org.curiouscrow.wizardservice.config.TemplateConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileTemplateProvider implements TemplateProvider {

    @Autowired
    private TemplateConfigProperties properties;

    @Override
    public TemplateItem loadTemplate(String templateName) {
        Path dirPath = Paths.get(properties.getSourcePath() + "/" + templateName);
        return new FolderTemplate(dirPath);
    }
}
