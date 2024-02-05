package ru.levolex.wizardservice.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FolderTemplate implements TemplateItem {

    private static final Logger logger = Logger.getLogger(FolderTemplate.class.getName());

    public static final int MAX_HIERARCHY_LEVEL = 5;

    Path path;

    List<TemplateItem> childrenItems = new ArrayList<>();

    public FolderTemplate(Path path) {
        this.path = path;

        try {
            Files.newDirectoryStream(path).forEach(item -> {
                if (Files.isDirectory(item)) {
                    logger.info("Directory template: " + item);
                    FolderTemplate template = new FolderTemplate(item);
                    this.childrenItems.add(template);
                } else {
                    FileTemplate template = new FileTemplate(item);
                    this.childrenItems.add(template);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasChildren() {
        return true;
    }

    @Override
    public List<TemplateItem> children() {
        return this.childrenItems;
    }

    @Override
    public String getName() {
        return String.valueOf(this.path.getFileName());
    }

    @Override
    public String templateText() {
        return "";
    }

    public Path getPath() {
        return this.path;
    }
}
