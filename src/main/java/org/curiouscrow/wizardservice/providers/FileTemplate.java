package org.curiouscrow.wizardservice.providers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

/**
 * File-based implementation of TemplateItem interface
 */
public class FileTemplate implements TemplateItem {

    private static final Logger logger = Logger.getLogger(FileTemplate.class.getName());

    private final String name;

    private final Path path;

    public FileTemplate(Path path) {
        this.path = path;
        this.name = String.valueOf(path.getFileName());
    }

    @Override
    public boolean hasChildren() {
        return false;
    }

    @Override
    public List<TemplateItem> children() {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String templateText() {
        try {
            return Files.readString(this.path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
