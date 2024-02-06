package org.curiouscrow.wizardservice.templateinfo;

import org.curiouscrow.wizardservice.entities.TemplateInfo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface TemplateDescriptionReader {

    List<TemplateInfo> readTemplateStructure() throws IOException;

    Map<String, TemplateInfo> readTemplateMap() throws IOException;
}
