package org.curiouscrow.wizardservice.manager;

import java.io.IOException;
import java.util.Map;

public interface TemplateManager {

    void prepareTemplate(String templateName, Map<String, String> values) throws IOException;
}
