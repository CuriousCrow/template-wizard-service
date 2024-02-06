package org.curiouscrow.wizardservice.utils;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.curiouscrow.wizardservice.entities.TemplateInfo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TemplateUtils {

    public static final String baseTemplateDir = "templates";

    public static final Logger logger = Logger.getLogger(TemplateUtils.class.getName());

    public static Map<String, String> getCompositeTemplate(String templateDir) throws IOException {

        Map<String, String> templateMap = new HashMap<>();

        Path dirPath = Paths.get(baseTemplateDir, templateDir);

        Files.newDirectoryStream(dirPath).forEach(path -> {
            try {
                if (Files.isDirectory(path)) {
                    logger.info("Directory ignored: " + path);
                } else {
                    String templateText = Files.readString(path);
                    String fileName = path.getFileName().toString();
                    templateMap.put(fileName, templateText);
                    logger.info(fileName + ": " + templateText);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return templateMap;
    }

    public static FolderTemplate getCompositeTemplateNew(String templateDir) {
        Path dirPath = Paths.get(baseTemplateDir, templateDir);
        return new FolderTemplate(dirPath);
    }

    public static String fillTemplateByValues(String templateStr, TemplateInfo values) {
        MustacheFactory mf = new DefaultMustacheFactory();
        try {
            Writer writer = new StringWriter();

            Mustache mustache = mf.compile(new StringReader(templateStr), "sampleTemplate");
            mustache.execute(writer, values);
            writer.flush();
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error:" + e.getMessage();
        }
    }
}
