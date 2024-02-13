package org.curiouscrow.wizardservice.manager;

import java.io.IOException;
import java.util.Map;

/**
 * Interface implementing filling template with property values
 * (can be implemented for different template engines)
 */
public interface TemplateManager {

    String prepareTemplate(String templateName, Map<String, String> values) throws IOException;
}
