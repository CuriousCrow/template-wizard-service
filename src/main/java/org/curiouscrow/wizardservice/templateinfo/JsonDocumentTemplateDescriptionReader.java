package org.curiouscrow.wizardservice.templateinfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.curiouscrow.wizardservice.entities.TemplateInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * JSON-document based implementation of TemplateDescriptionReader interface
 */
@Component
public class JsonDocumentTemplateDescriptionReader implements TemplateDescriptionReader {

    private static final Logger logger = Logger.getLogger(JsonDocumentTemplateDescriptionReader.class.getName());

    @Value("${templatewizard.source-path}")
    private String templatesDir;

    @Value("${templatewizard.descriptor-file}")
    private String templatesDescriptorFile;

    @Override
    public List<TemplateInfo> readTemplateStructure() throws IOException {
        String inputString = Files.readString(Paths.get(templatesDir, templatesDescriptorFile));

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TemplateInfo>>(){}.getType();
        return gson.<ArrayList<TemplateInfo>>fromJson(inputString, type);
    }

    @Override
    public Map<String, TemplateInfo> readTemplateMap() throws IOException {
        List<TemplateInfo> templates = readTemplateStructure();
        Map<String, TemplateInfo> templateMap = new HashMap<>();
        templates.forEach(item -> templateMap.put(item.getFolderName(), item));
        return templateMap;
    }
}
