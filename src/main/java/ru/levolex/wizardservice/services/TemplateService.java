package ru.levolex.wizardservice.services;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.levolex.wizardservice.entities.TemplateInfo;
import ru.levolex.wizardservice.utils.FolderTemplate;
import ru.levolex.wizardservice.utils.TemplateItem;
import ru.levolex.wizardservice.utils.TemplateUtils;
import ru.levolex.wizardservice.utils.ZipDirectory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TemplateService {

    private static final Logger logger = Logger.getLogger(TemplateService.class.getName());

    public void prepareTemplate(String templateName, Map<String, String> values) throws IOException {

        TemplateItem folderItem = TemplateUtils.getCompositeTemplateNew(templateName);
        logger.info("Template loaded: " + folderItem.getName());

        Path outPath = Files.createDirectories(Paths.get("out"));
        fillTemplate(folderItem, values, outPath);

        ZipDirectory.zipFolder(outPath.resolve(templateName), "results");
    }

    public void fillTemplate(TemplateItem item, Map<String, String> values, Path exportPath) {
        if (item.hasChildren()) {
            FolderTemplate folder = (FolderTemplate)item;
            try {
                Path newPath = exportPath.resolve(item.getName());
                Files.createDirectories(newPath);
                folder.children().forEach(templateItem -> fillTemplate(templateItem, values, newPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            MustacheFactory mf = new DefaultMustacheFactory();
            try {
                Writer writer = new FileWriter(exportPath.toString() + "/" + item.getName());

                Mustache mustache = mf.compile(new StringReader(item.templateText()), item.getName());
                mustache.execute(writer, values);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<TemplateInfo> readTemplateStructure() throws IOException {

        String inputString = Files.readString(Paths.get("templates", "templates.json"));

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TemplateInfo>>(){}.getType();
        ArrayList<TemplateInfo> outputList = gson.fromJson(inputString, type);
        logger.info("Hello from " + outputList);
        return outputList;
    }
}
