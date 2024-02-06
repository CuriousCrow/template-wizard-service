package org.curiouscrow.wizardservice.manager;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.curiouscrow.wizardservice.config.TemplateConfigProperties;
import org.curiouscrow.wizardservice.providers.FolderTemplate;
import org.curiouscrow.wizardservice.providers.TemplateItem;
import org.curiouscrow.wizardservice.providers.TemplateProvider;
import org.curiouscrow.wizardservice.utils.ZipDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class DefaultMustacheTemplateManager implements TemplateManager {

    private static final Logger logger = Logger.getLogger(DefaultMustacheTemplateManager.class.getName());

    @Autowired
    private TemplateProvider templateProvider;

    @Override
    public void prepareTemplate(String templateName, Map<String, String> values) throws IOException {
        TemplateItem folderItem = templateProvider.loadTemplate(templateName);
        logger.info("Template loaded: " + folderItem.getName());

        Path outPath = Files.createDirectories(Paths.get(TemplateConfigProperties.FILLED_TEMPLATE_FOLDER));
        fillTemplate(folderItem, values, outPath);

        ZipDirectory.zipFolder(outPath.resolve(templateName), TemplateConfigProperties.ZIPPED_TEMPLATE_FOLDER);
    }

    private void fillTemplate(TemplateItem item, Map<String, String> values, Path exportPath) {
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
}
